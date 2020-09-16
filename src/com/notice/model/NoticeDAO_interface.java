package com.notice.model;

import java.util.List;

public interface NoticeDAO_interface {
	public void insert(NoticeVO noticeVo);
	public void update (NoticeVO noticeVo);
	public void delete(String notNo);
	public NoticeVO findByPrimaryKey(String notNo);
	public List<NoticeVO> getAll();
}
