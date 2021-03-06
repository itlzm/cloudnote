package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.NoteService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class MoveNoteController {
	@Resource
	private NoteService service;
	@RequestMapping("/note/move.do")
	@ResponseBody
	public NoteResult execute(String bookId,String noteId){
		NoteResult result = service.moveNote(bookId, noteId);
		return result;
	}
}
