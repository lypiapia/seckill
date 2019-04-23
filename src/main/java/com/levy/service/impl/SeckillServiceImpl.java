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
	//��־����
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//�����ַ���(��ɱ�ӿ�)��salt�������û��³�md5��Խ����Խ��
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
		if(seckill == null) {	//δ�鵽����ɱ��¼
			return new Exposer(false, seckillId);
		}
		
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		
		//��ɱδ����
		if (startTime.getTime() > now.getTime() || endTime.getTime() < now.getTime()) {
			return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), 
					endTime.getTime());
		}
		
		//��ɱ������������ɱ��Ʒ��id���ø��ӿڼ��ܵ�md5
		String md5 = getMD5(seckillId);
		return new Exposer(true,md5,seckillId);
	}
	
	
	public String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	//��ɱ�Ƿ�ɹ����ɹ� ������棬������ϸ��ʧ�ܣ����쳣������ع�
	/**
	 * ʹ��ע��������񷽷����ŵ�:
	 * 1.�����Ŷ�һ��Լ������ȷ��ע���񷽷��ı�̷��
	 * 2.��֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ���������������
	 * 3.�������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if(md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite."); //���ݱ���д��
		}
		
		//ִ����ɱ�߼��������+������ϸ
		Date now = new Date();
		try {
			//�����
			int updateCount = seckillMapper.reduceNumber(seckillId, now);
			if(updateCount <= 0) {
				//û�и��¿�棬˵����ɱ����
				throw new SeckillCloseException("seckill is closed");
			}else {
				//���¿��ɹ�����ɱ�ɹ������ӹ�����ϸ
				int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
				//���Ƿ����ϸ���ظ����룬���û��Ƿ��ظ���ɱ
				if(insertCount <= 0) {
					throw new RepeatKillException("seckill repeated.");
				}else {
					//��ɱ�ɹ����õ��ɹ�������ϸ��¼�����سɹ���ɱ��Ϣ
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
			//������ʱ���쳣תΪ�������쳣
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}

}
