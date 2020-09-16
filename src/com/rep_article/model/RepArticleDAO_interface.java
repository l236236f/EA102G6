package com.rep_article.model;

import java.util.List;

public interface RepArticleDAO_interface {

//	新增
    public void insert(RepArticleVO repArticleVO);
    
//  修改
    public void update(RepArticleVO repArticleVO);
    
//  刪除
    public void delete(String repno);
    
//  用主鍵查詢
    public RepArticleVO findByPrimaryKey(String repno);
    
//  查詢全部
    public List<RepArticleVO> getAll();	
    
//  檢舉審核
    public void update_approved(String repno); 	  //檢舉通過
    public void update_notapproved(String repno); //檢舉未通過
	
}
