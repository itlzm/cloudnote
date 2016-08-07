package com.tedu.cloudnet.util;
/*
 * 自定义异常
 */
public class NoteException extends RuntimeException{
	public NoteException(String msg,Throwable t){
		super(msg,t);
	}
}
