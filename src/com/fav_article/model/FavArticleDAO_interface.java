package com.fav_article.model;

import java.util.List;

public interface FavArticleDAO_interface {
	
//	�s�W
    public void insert(FavArticleVO favArticleVO);
    
//  �R��
    public void delete(String memno, String artno);
    
//  �q�|���d��
    public List<FavArticleVO> findByPrimaryKey(String memno);
    
//  �d�ߥ���
    public List<FavArticleVO> getAll();
    
//  �C�X�峹���Ҧ��^��
    public List<FavArticleVO> getFavByArticle(String artno);    

}
