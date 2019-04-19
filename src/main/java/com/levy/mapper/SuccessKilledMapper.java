package com.levy.mapper;

import org.apache.ibatis.annotations.Param;

import com.levy.pojo.SuccessKilled;

public interface SuccessKilledMapper {
	
	/**
	 * ���빺����ϸ���ɹ����ظ�
	 * @param seckillId
	 * @param userPhone
	 * @return	
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, 
			@Param("userPhone") long userPhone);
	
	/**
	 * ����id��ѯSuccessKilled��Я����ɱ��Ʒʵ��
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	SuccessKilled queryByIdWithSecKill(@Param("seckillId") long seckillId,
			@Param("userPhone") long userPhone);
}
