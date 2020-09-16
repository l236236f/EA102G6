package com.rep_fos.model;

import java.util.List;

public interface RepFosDAOI {
	public void insert(RepFosVO repfosVO);
	
	public void update(RepFosVO repfosVO);
	
	public List<RepFosVO> findBymemNo(String memNo);
	
	public List<RepFosVO> getAll();
	
	public RepFosVO findByPrimaryKey(String repNo);
	
//  檢舉審核
    public void update_approved(String repNo); 	  //檢舉通過
    public void update_notapproved(String repNo); //檢舉未通過
    
}
