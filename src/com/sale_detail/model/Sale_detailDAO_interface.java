package com.sale_detail.model;

import java.util.List;

public interface Sale_detailDAO_interface {
	 public void insert(Sale_detailVO saleDetailVO);
     public void delete(String spNo, String prodNo);
     public Sale_detailVO findByPrimaryKey(String spNo, String prodNo);
     public List<Sale_detailVO> getAll();
}
