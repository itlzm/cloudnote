package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.ShareService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class LoadShareNoteController {
	
	@Resource
	private ShareService service;
	@RequestMapping("/note/loadShareNote.do")
	@ResponseBody
	public NoteResult execute(String shareId){
		NoteResult result = service.loadNote(shareId);
		return result;
	}
}
