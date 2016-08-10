package com.tedu.cloudnet.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.tedu.cloudnet.dao.BookDao;
import com.tedu.cloudnet.entity.Book;
import com.tedu.cloudnet.util.NoteResult;
import com.tedu.cloudnet.util.NoteUtil;
@Service
public class BookServiceImpl implements BookService{
	
	@Resource
	private BookDao dao;
	public NoteResult loadUserBooks(String id) {
		//创建返回结果
		NoteResult result = new NoteResult();
		//按照用户ID查询笔记本信息
		List<Book> books = dao.findByUserId(id);
		
		//若返回的books集合不为空isEmpty()
		if(!books.isEmpty()){
			result.setStatus(0);
			result.setMsg("有笔记");
			result.setData(books);
			return result;
		}
		result.setStatus(1);
		result.setMsg("无笔记");
		result.setData(books);
		return result;
	}
	//根据用户id添加笔记本
	public NoteResult addBook(String userId,String bookName){
		//userId,bookName为请求发送到服务器参数
		/*
		 * 构建Book对象
		 * cn_notebook_type_id 默认为5
		 * cn_notebook_desc 默认null
		 * cn_notebook_createtime 默认系统创建
		 */
		Book book = new Book();
		NoteResult result = new NoteResult();
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		long time = System.currentTimeMillis();
		book.setCn_notebook_createtime(new Timestamp(time));
		book.setCn_notebook_type_id("5");
		int rows = dao.save(book);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("创建笔记本成功");
			result.setData(book);
		}else{
			result.setStatus(1);
			result.setMsg("创建笔记本失败");
			result.setData(book);
		}
		return result;
	}
	//重命名book
	public NoteResult renameBook(String bookId,String bookName) {
		Book book = new Book();
		NoteResult result = new NoteResult();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		int i = dao.updateName(book);
		if(i==1){
			result.setStatus(0);
			result.setMsg("重命名成功");
		}else{
			result.setStatus(1);
			result.setMsg("重命名失败");
		}
		return result;
	}
	//根据bookId删除用户笔记本
	public NoteResult deleteBook(String bookId) {
		NoteResult result = new NoteResult();
		int i = dao.delete(bookId);
		if(i>=1){
			result.setStatus(0);
			result.setMsg("笔记本删除成功");
		}else{
			result.setStatus(1);
			result.setMsg("笔记本删除失败");
		}
		return result;
	}
}
