package com.levy.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.levy.exception.RepeatKillException;
import com.levy.exception.SeckillCloseException;
import com.levy.pojo.Exposer;
import com.levy.pojo.Seckill;
import com.levy.pojo.SeckillExecution;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml",
						"classpath:spring-service.xml"})
public class SeckillServiceTest {
	
	@Autowired
	private SeckillService seckillService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testGetSeckillList() throws Exception{
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
		
	}

	@Test
	public void testGetById() throws Exception{
		long seckillId = 1000;
		Seckill seckill = seckillService.getById(seckillId);
		logger.info("seckill={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() throws Exception{
		long seckillId = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		logger.info("exposer={}",exposer);
	}

	//4cd18749322e88ae2e39c47cd3b8c43f
	@Test
	public void testExecuteSeckill() throws Exception{
		long seckillId = 1000;
		long userPhone = 13888888887L;
		String md5 = "4cd18749322e88ae2e39c47cd3b8c43f";
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
			logger.info("result={}",seckillExecution);
		} catch (RepeatKillException e1) {
			logger.error(e1.getMessage());
		}catch (SeckillCloseException e2) {
			logger.error(e2.getMessage());
		}
	}
	
	
	@Test
	@Transactional	//加入@Transactional注解，自动进行回滚，不会对数据库产生变更
	//将第三个和第四个方法合并成一个完整逻辑的方法
	public void testSeckillLogic() throws Exception{
		long seckillId = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()) {
			logger.info("exposer={}",exposer);
			long phone = 13435479874L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
				logger.info("result={}",seckillExecution);
			} catch (RepeatKillException e1) {
				logger.error(e1.getMessage());
			}catch (SeckillCloseException e2) {
				logger.error(e2.getMessage());
			}
		}else {
			//秒杀未开启
			logger.info("exposer={}",exposer);
		}
	}
	
	@Test
	public void testSeckillByProcedure() {
		long seckillId = 1003;
		long phone = 13888888864L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()) {
			String md5 = exposer.getMd5();
			SeckillExecution seckillExecution = 
					seckillService.executeSeckillByProcedure(seckillId, phone, md5);
			logger.info("seckillExecution={}",seckillExecution);
			
		}
		
		
	}
	
}
