package com.tedu.cloudnet.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.eclipse.jdt.internal.compiler.ast.NumberLiteral;
import org.springframework.stereotype.Service;

import com.tedu.cloudnet.dao.NoteDao;
import com.tedu.cloudnet.entity.Note;
import com.tedu.cloudnet.util.NoteResult;
import com.tedu.cloudnet.util.NoteUtil;
@Service
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao dao;
	//加载笔记列表信息
	public NoteResult loadBookNotes(String id) {
		NoteResult result = new NoteResult();
		List<Map> notes = dao.findByBookId(id);
		if(!notes.isEmpty()){
			result.setStatus(0);
			result.setMsg("有笔记信息");
			result.setData(notes);
			return result;
		}
		result.setStatus(1);
		result.setMsg("无笔记信息");
		result.setData(notes);
		return result;
	}
	//根据笔记ID查询笔记信息
	public NoteResult loadNote(String id){
		Note note = dao.findById(id);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("笔记内容加载完毕");
		result.setData(note);
		return result;
	}
	//更新笔记信息
	public NoteResult updateNote(String title,String body,String id){
		Note note = new Note();
		NoteResult result = new NoteResult();
		note.setCn_note_id(id);
		note.setCn_note_body(body);
		note.setCn_note_title(title);
		note.setCn_note_create_time(System.currentTimeMillis());
		int rows = dao.updateNoteById(note);//该变量用于确定note是否更新成功
		if(rows==1){
			result.setStatus(0);
			result.setMsg("保存成功");
		}else{
			result.setStatus(1);
			result.setMsg("保存失败");
		}
		return result;
	}
	//添加笔记
	public NoteResult addNote(String noteTitle, String bookId, String userId) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		String noteId = NoteUtil.createId();
		note.setCn_note_title(noteTitle);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_id(noteId);
		//创建笔记时，将笔记body内容初始为"",
		//否则创建成功后，点击显示笔记信息时，浏览器控制台会出现错误
		note.setCn_note_body("");
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("1");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int i = dao.save(note);
		if(i==1){
			result.setStatus(0);
			result.setMsg("笔记创建成功");
			result.setData(noteId);
		}else{
			result.setStatus(1);
			result.setMsg("笔记创建失败");
		}
		return result;
	}

}
