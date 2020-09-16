package com.sprod_order.model;

import java.util.List;



public interface Sprod_orderDAO_interface {
	public void insert(Sprod_orderVO sprodOrderVO);
    public void update(Sprod_orderVO sprodOrderVO);
    public void delete(String orderNo);
    public Sprod_orderVO findByPrimaryKey(String orderNo);
    public List<Sprod_orderVO> findByMemNo(String memNo);
    public List<Sprod_orderVO> getAll();
}
