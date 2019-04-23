package com.levy.pojo;

import enums.SeckillStateEnum;

/**
 * 封装秒杀后的结果：成功还是失败
 * @author Levy
 *
 */
public class SeckillExecution {

	private long seckillId;
	
	//秒杀状态
	private int state;
	
	//状态铭文标识
	private String stateInfo;
	
	//当秒杀成功，需要传递秒杀成功的对象出去
	private SuccessKilled successKilled;
	
	//秒杀成功，返回所有信息
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getInfo();
		this.successKilled = successKilled;
	}
	
	//秒杀失败
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getInfo();
	}


	public long getSeckillId() {
		return seckillId;
	}


	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getStateInfo() {
		return stateInfo;
	}


	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}


	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}


	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled + "]";
	}
	
	
}
