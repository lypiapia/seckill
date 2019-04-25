package com.levy.service;

import java.util.List;

import com.levy.exception.RepeatKillException;
import com.levy.exception.SeckillCloseException;
import com.levy.exception.SeckillException;
import com.levy.pojo.Exposer;
import com.levy.pojo.Seckill;
import com.levy.pojo.SeckillExecution;

/**
 * 业务接口:站在使用者(程序员)的角度设计接口 三个方面:1.方法定义粒度，方法定义的要非常清楚2.参数，要越简练越好 3.返回类型(return
 * 类型一定要友好/或者return异常，我们允许的异常)
 */
public interface SeckillService {
	
	/**
	 * 查询全部秒杀记录
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	
	/**
	 * 根据seckillId查询单个记录
	 * @param seckillId 秒杀Id
	 * @return
	 */
	Seckill getById(long seckillId);
	
	
	/**
	 * 若秒杀开启，输出对应秒杀商品url，否则输出系统当前时间和秒杀时间
	 * @param seckillId 秒杀Id
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 秒杀有可能成功，有可能失败，所以需要抛出异常
	 * 		抛出RuntimeException，为了让Spring声明式事务能在秒杀失败情况下回滚事务。
	 * @param seckillId 秒杀Id
	 * @param userPhone	手机号码
	 * @param md5	md5加密
	 * @return	根据秒杀不同结果返回不同信息
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
	throws SeckillException,RepeatKillException,SeckillCloseException;
	
	/**
	 * 调用存储过程秒杀，不需要抛出异常
	 * 		为什么不用抛出异常？
	 * 			答：有关事务提交或回归交由mysql端的存储过程完成，不需要在程序中控制了。
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 */
	SeckillExecution executeSeckillByProcedure(long seckillId,long userPhone,String md5);
}
