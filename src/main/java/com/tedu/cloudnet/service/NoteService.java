package com.tedu.cloudnet.service;

import com.tedu.cloudnet.util.NoteResult;

public interface NoteService {
	public NoteResult loadBookNotes(String id);
	public NoteResult loadNote(String id);
	public NoteResult updateNote(String noteTitle,String noteBody,String noteId);
	public NoteResult addNote(String noteName,String bookId,String userId);
	public NoteResult deleteNote(String noteId);
	public NoteResult loadDeleteNote(String userId);
	public NoteResult moveNote(String bookId,String noteId);
	public NoteResult finalDeleteNote(String noteId);
	public NoteResult replayDeleteNote(String bookId,String noteId);
	public NoteResult searchNotes(String title,String status,String beginStr,String endStr);
}
