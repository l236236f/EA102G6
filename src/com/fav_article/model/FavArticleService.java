package com.fav_article.model;

import java.sql.Timestamp;
import java.util.List;

public class FavArticleService {
	
	private FavArticleDAO_interface dao;
	
	public FavArticleService() {
		dao = new FavArticleDAO();
//		dao = new FavArticleJNDIDAO();
	}
	
	public FavArticleVO addFavArticle(String memno, String artno, Timestamp favtime) {

		FavArticleVO favArticleVO = new FavArticleVO();

		favArticleVO.setMemno(memno);
		favArticleVO.setArtno(artno);
		favArticleVO.setFavtime(favtime);
		dao.insert(favArticleVO);		

		return favArticleVO;
	}

	public void deleteFavArticle(String memno, String artno) {
		dao.delete(memno, artno);
	}

	public List<FavArticleVO> getOneFavArticle(String memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<FavArticleVO> getAll() {
		return dao.getAll();
	}
	
	public List<FavArticleVO> getOneFavByArtno(String artno) {
		return dao.getFavByArticle(artno);
	}

}
