package com.sprod_order.model;

import java.sql.Date;
import java.util.List;

import com.shop_product.model.Shop_productVO;

public class Sprod_orderService {
	private Sprod_orderDAO_interface dao;
	
	public Sprod_orderService() {
//		dao = new Sprod_orderDAO();
		dao = new Sprod_orderJNDIDAO();
	}
	
	public Sprod_orderVO addSprodOrder(String memNo, Integer tranMethod, String tranAdd,String addresseeName, 
			String addresseeMail, Integer orderTotal, Integer orderStatus, String spNo) {

		Sprod_orderVO sprodOrderVO = new Sprod_orderVO();	
		sprodOrderVO.setMemNo(memNo);
		sprodOrderVO.setTranMethod(tranMethod);
		sprodOrderVO.setTranAdd(tranAdd);
		sprodOrderVO.setAddresseeName(addresseeName);
		sprodOrderVO.setAddresseeMail(addresseeMail);
		sprodOrderVO.setOrderTotal(orderTotal);
		sprodOrderVO.setOrderStatus(orderStatus);
		sprodOrderVO.setSpNo(spNo);
		dao.insert(sprodOrderVO);	
		return sprodOrderVO;
	}
	
	public Sprod_orderVO updateSprodOrder(String memNo, Integer tranMethod, String tranAdd,String addresseeName, 
			String addresseeMail, Integer orderTotal, Integer orderStatus, String spNo, String orderNo) {

		Sprod_orderVO sprodOrderVO = new Sprod_orderVO();	
		sprodOrderVO.setMemNo(memNo);
		sprodOrderVO.setTranMethod(tranMethod);
		sprodOrderVO.setTranAdd(tranAdd);
		sprodOrderVO.setAddresseeName(addresseeName);
		sprodOrderVO.setAddresseeMail(addresseeMail);
		sprodOrderVO.setOrderTotal(orderTotal);
		sprodOrderVO.setOrderStatus(orderStatus);
		sprodOrderVO.setSpNo(spNo);
		sprodOrderVO.setOrderNo(orderNo);
		dao.update(sprodOrderVO);	
		return sprodOrderVO;
	}
	
	public void deleteSprodOrder(String orderNo) {
		dao.delete(orderNo); 
	}
	
	public Sprod_orderVO getOneSprodOrderVO(String orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	
	public List<Sprod_orderVO> getAll(){
		return dao.getAll();	
	}
	
	public List<Sprod_orderVO> getOneMemSprodOrderVO(String memNo) {
		return dao.findByMemNo(memNo);
	}
}
