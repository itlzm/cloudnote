package com.tedu.cloudnet.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.tedu.cloudnet.dao.NoteDao;
import com.tedu.cloudnet.dao.ShareDao;
import com.tedu.cloudnet.entity.Note;
import com.tedu.cloudnet.entity.Share;
import com.tedu.cloudnet.util.NoteResult;
import com.tedu.cloudnet.util.NoteUtil;
@Service
public class ShareServiceImpl implements ShareService{

	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		Note note = noteDao.findById(noteId);
		String noteTypeId = note.getCn_note_type_id();
		System.out.println(noteTypeId);
		//检查cn_note_type_id是否为分享状态，
		//如果已分享，不执行下面逻辑
		if("2".equals(noteTypeId)){
			//如果noteTypeId为2，表示笔记已被分享
			result.setStatus(1);
			result.setMsg("该笔记已被分享");
		}else{
			//对未分享的笔记执行更新note_type_id操作，改为2
			noteDao.updateType(noteId);
			Share share = new Share();
			share.setCn_note_id(noteId);
			share.setCn_share_body(note.getCn_note_body());
			share.setCn_share_title(note.getCn_note_title());
			share.setCn_share_id(NoteUtil.createId());
			int i = shareDao.save(share);
			if(i>=1){
				result.setStatus(0);
				result.setMsg("分享成功");
			}else{
				result.setStatus(1);
				result.setMsg("分享失败");
			}
		}
		return result;
	}
	public NoteResult searchNote(String title,int page) {
		//处理查询条件
		if(title!=null && !"".equals(title)){
			title = "%"+title+"%";
		}
		//计算抓取起点
		if(page<1){
			page = 1;
		}
		int begin = (page-1)*5;
		//封装成map参数
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("begin",begin);
		params.put("title", title);
		List<Share> notes = shareDao.findlikeTitle(params);
		//封装返回结果
		NoteResult result = new NoteResult();
		if(notes.isEmpty()){
			result.setStatus(1);
			result.setMsg("无搜索结果");
		}else{
			result.setStatus(0);
			result.setMsg("有搜索结果");
			result.setData(notes);
		}
		return result;
	}
	public NoteResult loadNote(String shareId){
		Share shareNote = shareDao.loadShareNote(shareId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("分享笔记加载完成");
		result.setData(shareNote);
		return result;
	}
	
}
