package com.levy.exception;

/*
 * 秒杀业务相关所有的异常
 */
public class SeckillException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public SeckillException(String message) {
		super(message);
	}
	
	public SeckillException(String message,Throwable cause) {
		super(message,cause);
	}
}
