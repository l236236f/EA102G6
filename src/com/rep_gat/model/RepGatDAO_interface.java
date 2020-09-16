package com.rep_gat.model;

import java.util.List;

public interface RepGatDAO_interface {
	
	public void insert(RepGatVO repGatVO);
	public void update(RepGatVO repGatVO);
	public void delete(String repNo);
	public RepGatVO findByPrimaryKey(String repNo);
	public List<RepGatVO> getAll();
	
//  檢舉審核
    public void update_approved(String repno); 	  //檢舉通過
    public void update_notapproved(String repno); //檢舉未通過

}
