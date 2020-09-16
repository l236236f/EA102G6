package com.art_response.model;

import java.util.List;

public interface ArtResponseDAO_interface {
	
//	�s�W
    public void insert(ArtResponseVO artResponseVO);
    
//  �ק�
    public void update(ArtResponseVO artResponseVO);
    
//  �R��
    public void delete(String resno);
    
//  �ΥD��d��
    public ArtResponseVO findByPrimaryKey(String resno);
    
//  �d�ߥ���
    public List<ArtResponseVO> getAll();
    
//  �C�X�峹���Ҧ��^��
    public List<ArtResponseVO> getResByArticle(String artno);
    
//  �R���^��
    public void deleteResponse(String artno);
    
//  �ק�^��
    public void updateResponse(String resno);

}
