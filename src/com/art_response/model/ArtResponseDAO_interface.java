package com.art_response.model;

import java.util.List;

public interface ArtResponseDAO_interface {
	
//	新增
    public void insert(ArtResponseVO artResponseVO);
    
//  修改
    public void update(ArtResponseVO artResponseVO);
    
//  刪除
    public void delete(String resno);
    
//  用主鍵查詢
    public ArtResponseVO findByPrimaryKey(String resno);
    
//  查詢全部
    public List<ArtResponseVO> getAll();
    
//  列出文章的所有回覆
    public List<ArtResponseVO> getResByArticle(String artno);
    
//  刪除回覆
    public void deleteResponse(String artno);
    
//  修改回覆
    public void updateResponse(String resno);

}
