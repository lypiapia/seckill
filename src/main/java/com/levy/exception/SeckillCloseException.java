package com.levy.exception;

/*
 * ��ɱ�ѹر��쳣
 */
public class SeckillCloseException extends SeckillException{
	private static final long serialVersionUID = 1L;
	
	public SeckillCloseException(String message) {
		super(message);
	}
	
	public SeckillCloseException(String message,Throwable cause) {
		super(message,cause);
	}
}
