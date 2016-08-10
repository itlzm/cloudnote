package com.tedu.cloudnet.service;

import com.tedu.cloudnet.util.NoteResult;

public interface ShareService {
	public NoteResult shareNote(String noteId);
	public NoteResult searchNote(String title,int page);
	public NoteResult loadNote(String shareId);
}
