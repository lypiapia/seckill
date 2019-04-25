package com.levy.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.levy.pojo.Seckill;

public interface SeckillMapper {
	/**
	 * �����
	 * 
	 * @param seckillId 
	 * @param killTime
	 * @return	���µļ�¼���������<1��˵�������ʧ��
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * ������ɱid��ѯ��ɱ��Ʒ
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * ����ƫ������ѯ��ɱ��Ʒ�б�
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * ʹ�ô洢������ɱ
	 * @param paramMap
	 */
	void killByProcedure(Map<String, Object> paramMap);
	
	
}
