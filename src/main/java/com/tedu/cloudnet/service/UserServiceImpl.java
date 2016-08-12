package com.tedu.cloudnet.service;


import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.tedu.cloudnet.dao.UserDao;
import com.tedu.cloudnet.entity.User;
import com.tedu.cloudnet.util.NoteException;
import com.tedu.cloudnet.util.NoteResult;
import com.tedu.cloudnet.util.NoteUtil;
//扫描
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	//@Resource(name="userDao")
	@Resource
	private UserDao dao;//注入dao
	@Transactional(readOnly=true)//设置只读事务,默认为false
	//rollerbackFor默认RuntimeException,如果其它类型异常也需要回滚，指定rollbackFor=Ecxeption.class
	//propagation默认类型为required，事务传播类型
	public NoteResult checkLogin(String name, String password){
		
		NoteResult result = new NoteResult();
		User user = dao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			result.setData(null);
			return result;
		}
		//将用户输入的明文密码加密,然后与数据库中的密文密码比对是否相同
		try {
			String md5_pwd = NoteUtil.md5(password);
			if(!md5_pwd.equals(user.getCn_user_password())){
				result.setStatus(2);
				result.setMsg("密码错误");
				result.setData(null);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoteException("密码加密异常", e);
		}
		//登录成功
		result.setStatus(0);
		result.setMsg("登录成功");
		//将user对象存储在NoteResult前，将user的密码清空，保护用户的密码
		user.setCn_user_password("");
		result.setData(user);
		return result;
	}
	public NoteResult addUser(String name,String nick,String password){
		NoteResult result = new NoteResult();
		try {
			//检测是否重名
			User has_user = dao.findByName(name);
			if(has_user!=null){
				result.setStatus(1);
				result.setMsg("用户名被占用");
				return result;
			}
			//执行用户注册
			User user = new User();
			user.setCn_user_name(name);//设置用户名
			user.setCn_user_nick(nick);//设置昵称
			String md5_pwd = NoteUtil.md5(password);
			user.setCn_user_password(md5_pwd);//设置加密密码
			user.setCn_user_id(NoteUtil.createId());//设置Id,使用NoteUtil工具类生成UUID
			dao.save(user);
			//创建返回结果
			result.setStatus(0);
			result.setMsg("注册成功");
			return result;
		} catch (Exception e) {
			throw new NoteException("用户注册异常", e);
		}
	}
	public NoteResult checkUserName(String name){
		//检测是否重名
		NoteResult result = new NoteResult();
		User has_user = dao.findByName(name);
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("用户名被占用");
			return result;
		}
		result.setStatus(0);
		result.setMsg("用户名可用");
		return result;
	}
	public NoteResult modifyPwd(String lastPassword,String newPassword,String userId){
		NoteResult result = new NoteResult();
		User user = dao.findById(userId);
		String oldPassword = user.getCn_user_password();
		try {
			String md5_lastPassword = NoteUtil.md5(lastPassword);
			String md5_newPassword = NoteUtil.md5(newPassword);
			if(!oldPassword.equals(md5_lastPassword)){
				result.setStatus(1);
				result.setMsg("原密码错误");
			}else{
				user.setCn_user_password(md5_newPassword);
				dao.update(user);
				result.setStatus(0);
				result.setMsg("密码更改成功");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
