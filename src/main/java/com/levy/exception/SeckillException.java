package com.levy.exception;

/*
 * ��ɱҵ��������е��쳣
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
