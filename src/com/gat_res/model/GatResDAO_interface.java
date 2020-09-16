package com.gat_res.model;

import java.util.List;

public interface GatResDAO_interface {
	
	public void insert(GatResVO gatResVO);
	public void update(GatResVO gatResVO);
	public void reply(GatResVO gatResVO);
	public void delete(String resNo);
	public GatResVO findByPrimaryKey(String resNo);
	public List<GatResVO> getAll();

}
