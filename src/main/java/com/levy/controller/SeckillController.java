package com.levy.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.levy.exception.RepeatKillException;
import com.levy.exception.SeckillCloseException;
import com.levy.pojo.Exposer;
import com.levy.pojo.Seckill;
import com.levy.pojo.SeckillExecution;
import com.levy.pojo.SeckillResult;
import com.levy.service.SeckillService;

import enums.SeckillStateEnum;

@Controller
@RequestMapping("/seckill")	//url:模块/资源/{}/细分
public class SeckillController {
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		//list.jsp+mode=ModelAndView
        //获取列表页
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		for (Seckill seckill : list) {
			System.out.println(seckill.getCreateTime()+"=="+seckill.getStartTime()+"=="+seckill.getEndTime());
		}
		
		
		return "list";
	}
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable Long seckillId,Model model) {
		if(seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if(seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	//ajax,json暴露秒杀接口方法
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,
			produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		SeckillResult<Exposer> seckillResult;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			seckillResult = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			seckillResult = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return seckillResult;
	}
	
	//执行秒杀方法
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,
			produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(
			@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, 
			@CookieValue(value="userPhone",required=false) Long userPhone){
		
		if(userPhone == null) {
			return new SeckillResult<SeckillExecution>(false, "手机号未注册");
		}
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (RepeatKillException e1) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}catch (SeckillCloseException e2) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}catch (Exception e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
	}
	
	//获取系统时间接口
	@RequestMapping(value="/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> getTime(){
		Date date = new Date();
		return new SeckillResult<Long>(true, date.getTime());
	}
}
