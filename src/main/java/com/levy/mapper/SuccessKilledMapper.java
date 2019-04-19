package com.levy.mapper;

import org.apache.ibatis.annotations.Param;

import com.levy.pojo.SuccessKilled;

public interface SuccessKilledMapper {
	
	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return	
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, 
			@Param("userPhone") long userPhone);
	
	/**
	 * 根据id查询SuccessKilled并携带秒杀商品实体
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	SuccessKilled queryByIdWithSecKill(@Param("seckillId") long seckillId,
			@Param("userPhone") long userPhone);
}
