package com.tedu.cloudnet.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.BookService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class LoadBooksController {
	@Resource
	private BookService service;
	@RequestMapping("/book/loadbooks.do")
	@ResponseBody
	/*
	 * data:{"userId":userId},浏览器向服务器发送ajax请求数据
	 * execute方法中参数属性名应该与请求数据名一致
	 * 如果不一致，可以使用@RequestParam注解
	 * execute(@RequestParam(userId) String id)
	 */
	public NoteResult execute(String userId){
		NoteResult result = service.loadUserBooks(userId);
		return result;
	}
}
