package com.rep_article.model;

import java.sql.Timestamp;
import java.util.List;

public class RepArticleService {
	
	private RepArticleDAO_interface dao;
	
	public RepArticleService() {
		dao = new RepArticleDAO();
	}
	
	public RepArticleVO addRepArticle(String memno, String artno, String repreason) {

		RepArticleVO repArticleVO = new RepArticleVO();

		repArticleVO.setMemno(memno);
		repArticleVO.setArtno(artno);
		repArticleVO.setRepreason(repreason);
		dao.insert(repArticleVO);		

		return repArticleVO;
	}

	public RepArticleVO updateRepResponse(String repno, String memno, String artno, 
			   							  Timestamp reptime, String repreason, String repstatus) {

		RepArticleVO repArticleVO = new RepArticleVO();

		repArticleVO.setMemno(repno);
		repArticleVO.setMemno(memno);
		repArticleVO.setArtno(artno);
		repArticleVO.setReptime(reptime);
		repArticleVO.setRepreason(repreason);
		repArticleVO.setRepstatus(repstatus);
		dao.update(repArticleVO);		
		
		return repArticleVO;
	}

	public void deleteRepArticle(String repno) {
		dao.delete(repno);
	}

	public RepArticleVO getOneRepArticle(String repno) {
		return dao.findByPrimaryKey(repno);
	}

	public List<RepArticleVO> getAll() {
		return dao.getAll();
	}
	public void update_approved(String repno) {
		dao.update_approved(repno);
	}
	public void update_notapproved(String repno) {
		dao.update_notapproved(repno);
	}

}
