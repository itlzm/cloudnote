package test.dao;

import java.util.List;

import org.junit.Test;

import com.tedu.cloudnet.dao.BookDao;
import com.tedu.cloudnet.entity.Book;


public class TestBookDao extends BaseTest{
	@Test
	public void test1(){
		BookDao dao = ac.getBean("bookDao",BookDao.class);
		List<Book> books = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(books);
	}
}
