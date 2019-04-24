package com.levy.pojo;

public class SeckillResult<T> {
	
	private boolean isSuccess;
	
	private T data;
	
	private String error;
	
	
	public SeckillResult(boolean isSuccess, String error) {
		super();
		this.isSuccess = isSuccess;
		this.error = error;
	}

	public SeckillResult(boolean isSuccess, T data) {
		super();
		this.isSuccess = isSuccess;
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "SeckillResult [isSuccess=" + isSuccess + ", data=" + data + ", error=" + error + "]";
	}
	
	
}
