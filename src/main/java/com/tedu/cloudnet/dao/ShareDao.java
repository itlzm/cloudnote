package com.tedu.cloudnet.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnet.entity.Share;

public interface ShareDao {
	public int save(Share share);
	public Share loadShareNote(String id);
	public List<Share> findlikeTitle(Map<String,Object> params);
}
