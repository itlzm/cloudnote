package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.NoteService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class UpdateNoteController {
	@Resource
	private NoteService service;
	@RequestMapping("/note/update.do")
	@ResponseBody
	public NoteResult execute(String noteTitle,String noteBody,String noteId){
		NoteResult result = service.updateNote(noteTitle, noteBody, noteId);
		return result;
	}
}
