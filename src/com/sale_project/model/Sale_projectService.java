package com.sale_project.model;

import java.sql.Date;
import java.util.List;

public class Sale_projectService {
	private Sale_projectDAO_interface dao;
	
	public Sale_projectService() {
		dao = new Sale_projectDAO();
	}
	
	public Sale_projectVO addSaleProject(String venNo, String spName, Integer spQuan,Integer spTotPrice, 
			 Integer spTotOff, Date spStartTime, Date spEndTime, Integer spStatus) {

		Sale_projectVO saleProjectVO = new Sale_projectVO();	
		saleProjectVO.setVenNo(venNo);
		saleProjectVO.setSpName(spName);
		saleProjectVO.setSpQuan(spQuan);
		saleProjectVO.setSpTotPrice(spTotPrice);
		saleProjectVO.setSpTotOff(spTotOff);
		saleProjectVO.setSpStartTime(spStartTime);
		saleProjectVO.setSpEndTime(spEndTime);
		saleProjectVO.setSpStatus(spStatus);
		dao.insert(saleProjectVO);	
		
		return saleProjectVO;
	}
	
	public Sale_projectVO updateSaleProject(String venNo, String spName, Integer spQuan,Integer spTotPrice, 
			 Integer spTotOff, Date spStartTime, Date spEndTime, Integer spStatus, String spNo) {

		Sale_projectVO saleProjectVO = new Sale_projectVO();	
		saleProjectVO.setVenNo(venNo);
		saleProjectVO.setSpName(spName);
		saleProjectVO.setSpQuan(spQuan);
		saleProjectVO.setSpTotPrice(spTotPrice);
		saleProjectVO.setSpTotOff(spTotOff);
		saleProjectVO.setSpStartTime(spStartTime);
		saleProjectVO.setSpEndTime(spEndTime);
		saleProjectVO.setSpStatus(spStatus);
		saleProjectVO.setSpNo(spNo);
		dao.update(saleProjectVO);	
		
		return saleProjectVO;
	}
	
	public void deleteSaleProject(String spNo) {
		dao.delete(spNo); 
	}
	
	public Sale_projectVO getOneShopProductVO(String spNo) {
		return dao.findByPrimaryKey(spNo);
	}
	
	public List<Sale_projectVO> getAll(){
		return dao.getAll();	
	}
}
