package com.tedu.cloudnet.service;

import com.tedu.cloudnet.util.NoteResult;

public interface BookService {
	public NoteResult loadUserBooks(String id);
	public NoteResult addBook(String userId,String bookName);
	public NoteResult renameBook(String bookId,String bookName);
	public NoteResult deleteBook(String bookId);
}
