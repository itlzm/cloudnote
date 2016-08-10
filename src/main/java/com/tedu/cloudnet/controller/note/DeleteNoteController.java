package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;
import javax.print.attribute.standard.RequestingUserName;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.NoteService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class DeleteNoteController {
	@Resource
	private NoteService service;
	@RequestMapping("/note/delete.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = service.deleteNote(noteId);
		return result;
	}
}
