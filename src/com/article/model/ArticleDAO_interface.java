package com.article.model;

import java.util.List;

public interface ArticleDAO_interface {
	
//	新增
    public void insert(ArticleVO articleVO);
    
//  修改
    public void update(ArticleVO articleVO);
    
//  刪除
    public void delete(String artno);
    
//  用主鍵查詢
    public ArticleVO findByPrimaryKey(String artno);
    
//  查詢全部(不含檢舉)
    public List<ArticleVO> getAll();
    
//  檢舉
    public void update_report(String artno);
    
//  顯示全部檢舉
    public List<ArticleVO> getRepArt();
		    
}
