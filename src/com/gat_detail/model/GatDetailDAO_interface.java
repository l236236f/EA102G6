package com.gat_detail.model;

import java.util.List;


public interface GatDetailDAO_interface {
	
	public void insert(GatDetailVO gatDetailVO);
    public void delete(GatDetailVO gatDetailVO);
    public GatDetailVO findByPrimaryKey(String memNo);
    public List<GatDetailVO> getAll();

}
