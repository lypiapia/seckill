package com.levy.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.levy.pojo.SuccessKilled;


@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SuccessKilledMapperTest {
	
	@Autowired
	SuccessKilledMapper successKilledMapper;
	
	@Test
	public void testInsertSuccessKilled() {
		 long seckillId = 1000L; 
		 long userPhone = 13476191877L; 
		 int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone); 
		 System.out.println("insertCount=" + insertCount);
	}

	@Test
	public void testQueryByIdWithSecKill() {
		long seckillId = 1000L; 
		long userPhone = 13476191877L;
		SuccessKilled successKilled = successKilledMapper.queryByIdWithSecKill(seckillId, userPhone); 
		System.out.println(successKilled); 
		System.out.println(successKilled.getSeckill());
	}

}
