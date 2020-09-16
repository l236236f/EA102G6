package com.sprod_detail.model;

import java.util.List;

import com.shop_product.model.Shop_productVO;



public class Sprod_detailService {
	private Sprod_detailDAO_interface dao;
	
	public Sprod_detailService() {
//		dao = new Sprod_detailDAO();
		dao = new Sprod_detailJNDIDAO();
	}
	
	public Sprod_detailVO addSprodDetail(String orderNo, String prodNo, Integer quantity, Integer evaStar, String evaCont) {

		Sprod_detailVO sprodDetailVO = new Sprod_detailVO();	
		sprodDetailVO.setOrderNo(orderNo);
		sprodDetailVO.setProdNo(prodNo);
		sprodDetailVO.setQuantity(quantity);
		sprodDetailVO.setEvaStar(evaStar);
		sprodDetailVO.setEvaCont(evaCont);

		dao.insert(sprodDetailVO);	
		return sprodDetailVO;
	}
	
	public Sprod_detailVO updateSprodDetail(Integer evaStar, String evaCont, String orderNo, String prodNo) {
		
		Sprod_detailVO sprodDetailVO = new Sprod_detailVO();
		
		sprodDetailVO.setEvaStar(evaStar);
		sprodDetailVO.setEvaCont(evaCont);
		sprodDetailVO.setOrderNo(orderNo);
		sprodDetailVO.setProdNo(prodNo);
		
		dao.update(sprodDetailVO);
		
		return sprodDetailVO;
	}
	
	public void deleteSprodDetail(String orderNo, String prodNo) {
		dao.delete(orderNo, prodNo); 
	}
	
	public Sprod_detailVO getOneFavSprodVO(String orderNo, String prodNo) {
		return dao.findByPrimaryKey(orderNo, prodNo);
	}
	
	public List<Sprod_detailVO> getAll(){
		return dao.getAll();	
	}
	
	public List<Sprod_detailVO> findOneOrderDetail(String orderNo){
		return dao.findOneOrderDetail(orderNo);
	}
	
}
