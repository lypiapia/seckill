package com.levy.exception;

/*
 * �ظ���ɱ�쳣������ʱ�쳣������Ҫ�׳���try catch
 * Mysqlֻ֧������ʱ�쳣�Ļع�
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
