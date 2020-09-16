package com.rep_response.model;

import java.util.List;

public interface RepResponseDAO_interface {
	
//	新增
    public void insert(RepResponseVO repResponseVO);
    
//  修改
    public void update(RepResponseVO repResponseVO);
    
//  刪除
    public void delete(String repno);
    
//  用主鍵查詢
    public RepResponseVO findByPrimaryKey(String repno);
    
//  查詢全部
    public List<RepResponseVO> getAll();	

//  檢舉審核
    public void update_approved(String repno); 	  //檢舉通過
    public void update_notapproved(String repno); //檢舉未通過
    
}
