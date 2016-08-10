package com.tedu.cloudnet.service;


import com.tedu.cloudnet.util.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String name,String password);
	public NoteResult addUser(String name,String nick,String password);
	public NoteResult checkUserName(String name);
	public NoteResult modifyPwd(String lastPassword,String newPassword,String userId);
}
