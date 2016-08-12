package com.tedu.cloudnet.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnet.service.NoteService;
import com.tedu.cloudnet.util.NoteResult;

@Controller
public class SearchNotesController {
	@Resource
	private NoteService service;
	@RequestMapping("note/higsearch.do")
	@ResponseBody
	public NoteResult execute(String title,String status,String beginStr,String endStr){
		NoteResult result = service.searchNotes(title, status, beginStr, endStr);
		return result;
	}
}
