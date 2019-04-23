package com.levy.exception;

/*
 * 重复秒杀异常是运行时异常，不需要抛出或try catch
 * Mysql只支持运行时异常的回滚
 */
public class RepeatKillException extends SeckillException{
	private static final long serialVersionUID = 1L;

	public RepeatKillException(String message) {
		super(message);
	}
	
	public RepeatKillException(String message,Throwable cause) {
		super(message,cause);
	}
}
