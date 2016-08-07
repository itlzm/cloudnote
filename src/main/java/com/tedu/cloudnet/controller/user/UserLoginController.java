package com.tedu.cloudnet.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class UserLoginController {
	//@Resource(name="userService")
	@Resource
	private UserService service;
	@RequestMapping("/user/login.do")
	@ResponseBody
	public NoteResult execute(String name,String password){
		NoteResult result = service.checkLogin(name, password);
		return result;
	}
}
