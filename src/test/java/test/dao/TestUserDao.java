package test.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnet.dao.UserDao;
import com.tedu.cloudnet.entity.User;
import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.service.UserServiceImpl;
import com.tedu.cloudnet.util.NoteResult;

public class TestUserDao {
	@Test
	public void test1() throws SQLException{
		String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		DataSource ds = ac.getBean("dbcp",DataSource.class);
		Connection conn = ds.getConnection();
		System.out.println(conn);
		conn.close();
		SqlSessionFactory factory = ac.getBean("ssf",SqlSessionFactory.class);
		System.out.println(factory);
		UserDao dao = ac.getBean("userDao", UserDao.class);
		User user = dao.findByName("demo");
		if(user==null){
			System.out.println("用户不存在");
		}else{
			System.out.println("password:"+user.getCn_user_password());
		}
		UserService userService = ac.getBean("userService",UserServiceImpl.class);
		NoteResult rs = userService.checkLogin("demo1", "123");
		System.out.println(rs);
	}
}
