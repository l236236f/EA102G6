package com.fav_article.model;

import java.util.List;

public interface FavArticleDAO_interface {
	
//	新增
    public void insert(FavArticleVO favArticleVO);
    
//  刪除
    public void delete(String memno, String artno);
    
//  從會員查詢
    public List<FavArticleVO> findByPrimaryKey(String memno);
    
//  查詢全部
    public List<FavArticleVO> getAll();
    
//  列出文章的所有回覆
    public List<FavArticleVO> getFavByArticle(String artno);    

}
