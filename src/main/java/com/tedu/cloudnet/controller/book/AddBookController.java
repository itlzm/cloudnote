package com.tedu.cloudnet.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.BookService;
import com.tedu.cloudnet.util.NoteResult;
@Controller
public class AddBookController {
	@Resource
	private BookService service;
	@RequestMapping("/book/add.do")
	@ResponseBody
	public NoteResult execute(String userId,String bookName){
		NoteResult result = service.addBook(userId, bookName);
		return result;
	}
}
