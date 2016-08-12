package com.tedu.cloudnet.controller.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * 封装打桩操作逻辑
 */
@Component//扫描
@Aspect//指定为切面
public class LoggerBean {
	//指定通知类型,切入点表达式
	@Before("within(com.tedu.cloudnet.controller..*)")
	public void loggerController(){
		System.out.println("进入Controller组件");
	}
}
