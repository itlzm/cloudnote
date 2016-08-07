package test.service;

import org.junit.Test;

import com.tedu.cloudnet.service.BookService;
import com.tedu.cloudnet.service.BookServiceImpl;
import com.tedu.cloudnet.util.NoteResult;

public class TestFindAllBook extends BaseTest{
	@Test
	public void test1(){
		BookService service = ac.getBean("bookServiceImpl", BookServiceImpl.class);
		NoteResult result = service.loadUserBooks("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(result);
	}
}
