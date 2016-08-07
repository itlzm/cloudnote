package com.tedu.cloudnet.service;

import com.tedu.cloudnet.util.NoteResult;

public interface NoteService {
	public NoteResult loadBookNotes(String id);
	public NoteResult loadNote(String id);
	public NoteResult updateNote(String noteTitle,String noteBody,String noteId);
	public NoteResult addNote(String noteName,String bookId,String userId);
}
