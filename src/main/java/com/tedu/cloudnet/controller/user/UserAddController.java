package com.tedu.cloudnet.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class UserAddController {
	@Resource
	private UserService service;
	@RequestMapping("/user/add.do")
	@ResponseBody
	public NoteResult execute(String name,String nick,String password){
		NoteResult result = service.addUser(name, nick, password);
		return result;
	}
}
