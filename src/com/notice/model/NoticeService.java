package com.notice.model;

import java.util.List;

public class NoticeService {

	private NoticeDAO_interface dao;

	public NoticeService() {
		dao = new NoticeDAO();
	}

	
	public NoticeVO addNotice(String memNo, String notClass, String notCont) {

		NoticeVO noticeVO = new NoticeVO();

		noticeVO.setMemNo(memNo);
		noticeVO.setNotClass(notClass);
		noticeVO.setNotCont(notCont);
		
		dao.insert(noticeVO);

		return noticeVO;
	}
	

	public NoticeVO updateNotice(String notNo,String memNo, String notClass, String notCont) {

		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO.setNotNo(notNo);
		noticeVO.setMemNo(memNo);
		noticeVO.setNotClass(notClass);
		noticeVO.setNotCont(notCont);
		
		dao.update(noticeVO);
		
		return noticeVO;
	}

	public void deleteNotice(String notNo) {
		dao.delete(notNo);
	}

	public NoticeVO getOneNotice(String notNo) {
		return dao.findByPrimaryKey(notNo);
	}

	public List<NoticeVO> getAll() {
		return dao.getAll();
	}
}

