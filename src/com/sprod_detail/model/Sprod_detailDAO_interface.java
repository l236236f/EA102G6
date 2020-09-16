package com.sprod_detail.model;

import java.util.List;

public interface Sprod_detailDAO_interface {
	public void insert(Sprod_detailVO sprodDetailVO);
    public void delete(String orderNo, String prodNo);
    public void update(Sprod_detailVO sprodDetailVO);
    public Sprod_detailVO findByPrimaryKey(String orderNo, String prodNo);
    public List<Sprod_detailVO> getAll();
    public List<Sprod_detailVO> findOneOrderDetail(String orderNo);
}
