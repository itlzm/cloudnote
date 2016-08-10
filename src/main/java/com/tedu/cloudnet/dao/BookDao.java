package com.tedu.cloudnet.dao;

import java.util.List;


import com.tedu.cloudnet.entity.Book;

public interface BookDao {
	public List<Book> findByUserId(String id);
	public int save(Book book);
	public int updateName(Book book);
	public int delete(String id);
}
