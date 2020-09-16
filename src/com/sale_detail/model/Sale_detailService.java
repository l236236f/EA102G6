package com.sale_detail.model;

import java.util.List;

public class Sale_detailService {
private Sale_detailDAO_interface dao;
	
	public Sale_detailService() {
		dao = new Sale_detailDAO();
	}
	
	public Sale_detailVO addSaleDetailVO(String spNo, String prodNo) {

		Sale_detailVO saleDetailVO = new Sale_detailVO();	
		saleDetailVO.setSpNo(spNo);
		saleDetailVO.setProdNo(prodNo);
		dao.insert(saleDetailVO);	
		return saleDetailVO;
	}
	
	public void deleteSaleDetail(String spNo, String prodNo) {
		dao.delete(spNo, prodNo); 
	}
	
	public Sale_detailVO getOneShopProductVO(String spNo, String prodNo) {
		return dao.findByPrimaryKey(spNo, prodNo);
	}
	
	public List<Sale_detailVO> getAll(){
		return dao.getAll();	
	}
}
