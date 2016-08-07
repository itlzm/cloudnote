package com.tedu.cloudnet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class NoteUtil {
	/*
	 * 将传入的src加密处理明文字符串，返回加密后的字符串结果
	 */
	public static String md5(String src) throws NoSuchAlgorithmException{
		/*
		 * 将字符串信息采用MD5处理,也可用于验证文件是否一致
		 */
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] output = md.digest(src.getBytes());
		//将MD5处理结果利用Base64转成字符串
		String str = Base64.encodeBase64String(output);
		return str;
	}
	/*
	 * 随机生成uuid，用于表中主键
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(md5("123456"));
		System.out.println(md5("123"));
		System.out.println(md5("123123"));
		System.out.println(md5("123123456"));
	}
}
