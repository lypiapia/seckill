package com.levy.service;

import java.util.List;

import com.levy.exception.RepeatKillException;
import com.levy.exception.SeckillCloseException;
import com.levy.exception.SeckillException;
import com.levy.pojo.Exposer;
import com.levy.pojo.Seckill;
import com.levy.pojo.SeckillExecution;

/**
 * ҵ��ӿ�:վ��ʹ����(����Ա)�ĽǶ���ƽӿ� ��������:1.�����������ȣ����������Ҫ�ǳ����2.������ҪԽ����Խ�� 3.��������(return
 * ����һ��Ҫ�Ѻ�/����return�쳣������������쳣)
 */
public interface SeckillService {
	
	/**
	 * ��ѯȫ����ɱ��¼
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	
	/**
	 * ����seckillId��ѯ������¼
	 * @param seckillId ��ɱId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	
	/**
	 * ����ɱ�����������Ӧ��ɱ��Ʒurl���������ϵͳ��ǰʱ�����ɱʱ��
	 * @param seckillId ��ɱId
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ��ɱ�п��ܳɹ����п���ʧ�ܣ�������Ҫ�׳��쳣
	 * @param seckillId ��ɱId
	 * @param userPhone	�ֻ�����
	 * @param md5	md5����
	 * @return	������ɱ��ͬ������ز�ͬ��Ϣ
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
	throws SeckillException,RepeatKillException,SeckillCloseException;
}
