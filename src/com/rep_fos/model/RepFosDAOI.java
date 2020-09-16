package com.rep_fos.model;

import java.util.List;

public interface RepFosDAOI {
	public void insert(RepFosVO repfosVO);
	
	public void update(RepFosVO repfosVO);
	
	public List<RepFosVO> findBymemNo(String memNo);
	
	public List<RepFosVO> getAll();
	
	public RepFosVO findByPrimaryKey(String repNo);
	
//  ���|�f��
    public void update_approved(String repNo); 	  //���|�q�L
    public void update_notapproved(String repNo); //���|���q�L
    
}
