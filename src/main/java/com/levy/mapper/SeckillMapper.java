package com.levy.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.levy.pojo.Seckill;

public interface SeckillMapper {
	/**
	 * 减库存
	 * 
	 * @param seckillId 
	 * @param killTime
	 * @return	更新的记录行数，如果<1则说明减库存失败
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * 根据秒杀id查询秒杀商品
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * 使用存储过程秒杀
	 * @param paramMap
	 */
	void killByProcedure(Map<String, Object> paramMap);
	
	
}
