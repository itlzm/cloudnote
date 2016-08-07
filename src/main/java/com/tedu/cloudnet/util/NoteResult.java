package com.tedu.cloudnet.util;

import java.io.Serializable;

public class NoteResult implements Serializable{
	private int status;//0 表示成功，1 表示用户名不存在，2 表示密码错误
	private String msg;//消息
	private Object data;//返回的数据
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
