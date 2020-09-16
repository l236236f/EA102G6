package com.fav_gat.model;

import java.util.List;

public interface FavGatDAO_interface {
	
	public void insert(FavGatVO favGatVO);
	public void delete(FavGatVO favGatVO);
	public FavGatVO findByPrimaryKey(String gatNo);
	public List<FavGatVO> getAll();

}
