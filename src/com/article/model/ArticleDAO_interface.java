package com.article.model;

import java.util.List;

public interface ArticleDAO_interface {
	
//	�s�W
    public void insert(ArticleVO articleVO);
    
//  �ק�
    public void update(ArticleVO articleVO);
    
//  �R��
    public void delete(String artno);
    
//  �ΥD��d��
    public ArticleVO findByPrimaryKey(String artno);
    
//  �d�ߥ���(���t���|)
    public List<ArticleVO> getAll();
    
//  ���|
    public void update_report(String artno);
    
//  ��ܥ������|
    public List<ArticleVO> getRepArt();
		    
}
