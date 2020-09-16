package com.gat_eva.model;

import java.util.List;

public interface GatEvaDAO_interface {
	
	public void insert(GatEvaVO gatEvaVO);
    public void update(GatEvaVO gatEvaVO);
    public GatEvaVO findByPrimaryKey(String memNo, String gatNo);
    public List<GatEvaVO> getAll();

}
