package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.ShareService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class SearchShareNoteController {
	@Resource
	private ShareService service;
	@RequestMapping("/note/search_share.do")
	@ResponseBody
	public NoteResult execute(String noteTitle,int page){
		NoteResult result = service.searchNote(noteTitle,page);
		return result;
	}
}
