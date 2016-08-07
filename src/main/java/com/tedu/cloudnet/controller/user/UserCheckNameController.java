package com.tedu.cloudnet.controller.user;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class UserCheckNameController {
	@Resource
	private UserService service;
	@RequestMapping("/user/check.do")
	@ResponseBody
	public NoteResult execute(String name){
		NoteResult result = service.checkUserName(name);
		return result;
	}
}
