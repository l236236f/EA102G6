package com.article.model;

import java.util.List;

public class ArticleService {
	
	private ArticleDAO_interface dao;
	
	public ArticleService() {
		dao = new ArticleDAO();
//		dao = new ArticleJNDIDAO();
	}
	
	public ArticleVO addArticle(String memno, String arttitle, String artcontent) {
		
		ArticleVO articleVO = new ArticleVO();

		articleVO.setMemno(memno);
		articleVO.setArttitle(arttitle);
		articleVO.setArtcontent(artcontent);
		dao.insert(articleVO);		
		
		return articleVO;
	}
	
	public ArticleVO updateArticle(String artno, String memno, String arttitle, String artcontent) {
		
		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArtno(artno);
		articleVO.setMemno(memno);
		articleVO.setArttitle(arttitle);
		articleVO.setArtcontent(artcontent);
		dao.update(articleVO);		
		
		return getOneArticle(artno);  // 從資料庫抓資料
	}
	
	public void deleteArticle(String artno) {
		dao.delete(artno);
	}

	public ArticleVO getOneArticle(String artno) {
		return dao.findByPrimaryKey(artno);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public void update_report(String artno) {
		dao.update_report(artno);
	}
	
	public List<ArticleVO> getRepArt() {
		return dao.getRepArt();
	}
	
}
