package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.NoteService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class LoadNotesController {

	@Resource
	private NoteService service;
	@RequestMapping("/note/loadnotes.do")
	@ResponseBody
	/*
	 * 方法中参数名bookId需要与浏览器通过ajax发送的data数据的名字一致
	 */
	public NoteResult execute(String bookId){
		NoteResult result = service.loadBookNotes(bookId);
		return result;
	}
}
