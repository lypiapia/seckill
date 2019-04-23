package com.levy.pojo;

import enums.SeckillStateEnum;

/**
 * ��װ��ɱ��Ľ�����ɹ�����ʧ��
 * @author Levy
 *
 */
public class SeckillExecution {

	private long seckillId;
	
	//��ɱ״̬
	private int state;
	
	//״̬���ı�ʶ
	private String stateInfo;
	
	//����ɱ�ɹ�����Ҫ������ɱ�ɹ��Ķ����ȥ
	private SuccessKilled successKilled;
	
	//��ɱ�ɹ�������������Ϣ
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getInfo();
		this.successKilled = successKilled;
	}
	
	//��ɱʧ��
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
