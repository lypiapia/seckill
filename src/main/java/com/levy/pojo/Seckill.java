package com.levy.pojo;

/**
 * 这里做下笔记。
 * 因为Date包引错，引成了java.sql.Date类，导致protoStuff反序列化失败
 * 所以在java程序中要使用java.util.Date
 */
import java.util.Date;

public class Seckill {
	
	private Long seckillId;
	
	private String name;
	
	private Integer number;
	
	private Date startTime;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	private Date createTime;
	
	private Date endTime;

	public Long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Seckill [seckillId=" + seckillId + ", name=" + name + ", number=" + number + ", createTime="
				+ createTime + ", endTime=" + endTime + "]";
	}
	
}
