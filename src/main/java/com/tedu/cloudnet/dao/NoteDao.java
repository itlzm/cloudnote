package com.tedu.cloudnet.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnet.entity.Note;

public interface NoteDao {
	public List<Map> findByBookId(String id);
	public Note findById(String id);
	public int updateNoteById(Note note);
	public int save(Note note);
}
