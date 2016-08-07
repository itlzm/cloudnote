package test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.util.NoteResult;

public class TestAddUser extends BaseTest{
	//测试addUser方法
	@Test
	public void test1(){
		UserService service = ac.getBean("userService", UserService.class);
		NoteResult result = service.addUser("dem", "","123");
		System.out.println(result);
	}
}
