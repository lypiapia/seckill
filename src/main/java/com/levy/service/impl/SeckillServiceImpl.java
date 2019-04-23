package com.levy.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.levy.exception.RepeatKillException;
import com.levy.exception.SeckillCloseException;
import com.levy.exception.SeckillException;
import com.levy.mapper.SeckillMapper;
import com.levy.mapper.SuccessKilledMapper;
import com.levy.pojo.Exposer;
import com.levy.pojo.Seckill;
import com.levy.pojo.SeckillExecution;
import com.levy.pojo.SuccessKilled;
import com.levy.service.SeckillService;

import enums.SeckillStateEnum;

@Service
public class SeckillServiceImpl implements SeckillService{
	//日志对象
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//混淆字符串(秒杀接口)的salt，避免用户猜出md5，越复杂越好
	private final String salt = "clk1bjij35r^6%152&@HUFJ2JAHL";
	
	@Autowired
	private SeckillMapper seckillMapper;
	
	@Autowired
	private SuccessKilledMapper successKilledMapper;
	
	public List<Seckill> getSeckillList() {
		return seckillMapper.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		return seckillMapper.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillMapper.queryById(seckillId);
		if(seckill == null) {	//未查到该秒杀记录
			return new Exposer(false, seckillId);
		}
		
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		
		//秒杀未开启
		if (startTime.getTime() > now.getTime() || endTime.getTime() < now.getTime()) {
			return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), 
					endTime.getTime());
		}
		
		//秒杀开启，返回秒杀商品的id，用给接口加密的md5
		String md5 = getMD5(seckillId);
		return new Exposer(true,md5,seckillId);
	}
	
	
	public String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	//秒杀是否成功。成功 ：减库存，增加明细；失败：抛异常，事务回滚
	/**
	 * 使用注解控制事务方法的优点:
	 * 1.开发团队一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if(md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite."); //数据被重写了
		}
		
		//执行秒杀逻辑，减库存+增加明细
		Date now = new Date();
		try {
			//减库存
			int updateCount = seckillMapper.reduceNumber(seckillId, now);
			if(updateCount <= 0) {
				//没有更新库存，说明秒杀结束
				throw new SeckillCloseException("seckill is closed");
			}else {
				//更新库存成功，秒杀成功，增加购买明细
				int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
				//看是否该明细被重复插入，即用户是否重复秒杀
				if(insertCount <= 0) {
					throw new RepeatKillException("seckill repeated.");
				}else {
					//秒杀成功，得到成功插入明细记录，返回成功秒杀信息
					SuccessKilled successKilled = successKilledMapper.queryByIdWithSecKill(seckillId, userPhone);
					return new SeckillExecution(seckillId,SeckillStateEnum.SUCCESS,successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		}catch (RepeatKillException e2) {
			throw e2;
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			//将编译时期异常转为运行期异常
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}

}
