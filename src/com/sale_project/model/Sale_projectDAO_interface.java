package com.sale_project.model;

import java.util.List;

public interface Sale_projectDAO_interface {
	
	public void insert(Sale_projectVO saleProjectVO);
	public void update(Sale_projectVO saleProjectVO);
	public void delete(String spNo);
	public Sale_projectVO findByPrimaryKey(String spNo);
	public List<Sale_projectVO> getAll();
	
}
