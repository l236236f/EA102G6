package com.rep_response.model;

import java.util.List;

public interface RepResponseDAO_interface {
	
//	�s�W
    public void insert(RepResponseVO repResponseVO);
    
//  �ק�
    public void update(RepResponseVO repResponseVO);
    
//  �R��
    public void delete(String repno);
    
//  �ΥD��d��
    public RepResponseVO findByPrimaryKey(String repno);
    
//  �d�ߥ���
    public List<RepResponseVO> getAll();	

//  ���|�f��
    public void update_approved(String repno); 	  //���|�q�L
    public void update_notapproved(String repno); //���|���q�L
    
}
