package com.levy.mapper;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.levy.pojo.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SeckillMapperTest {
	
	@Autowired
	private SeckillMapper seckillMapper;
	
	@Test
	public void testReduceNumber() {
		long seckillId = 1000;
		Date date = new Date();
		int updateCount = seckillMapper.reduceNumber(seckillId, date);
		System.out.println(updateCount);
	}

	@Test
	public void testQueryById() {
		long seckillId = 1000; 
		Seckill seckill = seckillMapper.queryById(seckillId); 
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() {
		List<Seckill> list = seckillMapper.queryAll(0, 100);
		for (Seckill seckill : list) {
			System.out.println(seckill);
		}
	}

}
