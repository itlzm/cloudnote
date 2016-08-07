package com.tedu.cloudnet.dao;

import com.tedu.cloudnet.entity.User;

public interface UserDao {
	public User findByName(String name);
	public User findById(String id);
	public void save(User user);
	public void update(User user);
}
