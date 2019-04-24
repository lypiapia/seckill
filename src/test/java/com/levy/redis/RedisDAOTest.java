package com.levy.redis;



import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.levy.mapper.SeckillMapper;
import com.levy.pojo.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-dao.xml" })
	public class RedisDAOTest {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		private long id = 1001;

	 @Autowired
	 private RedisDAO redisDao;
	
	 @Autowired
	 private SeckillMapper seckillMapper;
	
	 @Test
	 public void testSeckill() {
	     Seckill seckill = redisDao.getSeckill(id);
	     if (seckill == null) {
	         seckill = seckillMapper.queryById(id);
	         if (seckill != null) {
	             String result = redisDao.putSeckill(seckill);
	             logger.info("result={}", result);
	             seckill = redisDao.getSeckill(id);
	             logger.info("seckill={}", seckill);
	         }
	     }
	 }
}
