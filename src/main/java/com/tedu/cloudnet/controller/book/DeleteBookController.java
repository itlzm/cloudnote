package com.tedu.cloudnet.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.BookService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class DeleteBookController {
	@Resource
	private BookService service;
	@RequestMapping("/book/delete.do")
	@ResponseBody
	public NoteResult execute(String bookId){
		NoteResult result = service.deleteBook(bookId);
		return result;
	}
}
