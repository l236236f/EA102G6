package com.rep_article.model;

import java.util.List;

public interface RepArticleDAO_interface {

//	�s�W
    public void insert(RepArticleVO repArticleVO);
    
//  �ק�
    public void update(RepArticleVO repArticleVO);
    
//  �R��
    public void delete(String repno);
    
//  �ΥD��d��
    public RepArticleVO findByPrimaryKey(String repno);
    
//  �d�ߥ���
    public List<RepArticleVO> getAll();	
    
//  ���|�f��
    public void update_approved(String repno); 	  //���|�q�L
    public void update_notapproved(String repno); //���|���q�L
	
}
