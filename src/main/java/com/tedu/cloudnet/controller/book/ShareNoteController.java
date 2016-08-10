package com.tedu.cloudnet.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.ShareService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class ShareNoteController {
	@Resource
	private ShareService service;
	@RequestMapping("/note/share.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = service.shareNote(noteId);
		return result;
	}
}
