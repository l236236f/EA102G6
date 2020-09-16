package com.rep_gat.model;

import java.util.List;

public interface RepGatDAO_interface {
	
	public void insert(RepGatVO repGatVO);
	public void update(RepGatVO repGatVO);
	public void delete(String repNo);
	public RepGatVO findByPrimaryKey(String repNo);
	public List<RepGatVO> getAll();
	
//  ���|�f��
    public void update_approved(String repno); 	  //���|�q�L
    public void update_notapproved(String repno); //���|���q�L

}
