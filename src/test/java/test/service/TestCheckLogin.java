package test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.util.NoteResult;

public class TestCheckLogin {
	@Test
	public void test1(){
		String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		UserService service = ac.getBean("userService", UserService.class);
		NoteResult result = service.checkLogin("demo", "123");
		System.out.println(result);
	}
}
