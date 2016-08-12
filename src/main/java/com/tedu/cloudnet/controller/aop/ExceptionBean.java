package com.tedu.cloudnet.controller.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/*
 * 切面：将异常信息写入文件
 */
@Component//扫描
@Aspect//定义为切面
public class ExceptionBean {
	//指定异常通知和切入点表达式
	@AfterThrowing(throwing="e",pointcut="within(com.tedu.cloudnet.controller..*)")
	public void execute(Exception e){
		//e就是将来目标方法抛出的异常类型
		try {
			//将e的信息写入note_error.log文件
			FileWriter fw = new FileWriter("D:\\note_erro.log",true);
			PrintWriter pw = new PrintWriter(fw);
			//打印异常头部描述
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			//将异常栈信息打印
			pw.println("*******************");
			pw.println("**发生时间："+time);
			pw.println("**异常类型："+e);
			pw.println("*******************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
