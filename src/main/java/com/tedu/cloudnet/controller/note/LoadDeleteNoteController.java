package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.NoteService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class LoadDeleteNoteController {

	@Resource
	private NoteService service;
	@RequestMapping("/note/rollback.do")
	@ResponseBody
	public NoteResult execute(String userId){
		NoteResult result = service.loadDeleteNote(userId);
		return result;
	}
}
