package com.tedu.cloudnet.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.UserService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class ModifyUserPwdController {

	@Resource
	private UserService service;
	@RequestMapping("/user/modifyPwd.do")
	@ResponseBody
	public NoteResult execute(String lastPassword,String newPassword,String userId){
		NoteResult result = service.modifyPwd(lastPassword,newPassword,userId);
		return result;
	}
}
