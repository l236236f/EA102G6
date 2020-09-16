package com.fav_sprod.model;

import java.util.List;

public class Fav_sprodService {
private Fav_sprodDAO_interface dao;
	
	public Fav_sprodService() {
		dao = new Fav_sprodDAO();
	}
	
	public Fav_sprodVO addFavSprod(String memNo, String prodNo) {

		Fav_sprodVO favSprodVO = new Fav_sprodVO();	
		favSprodVO.setMemNo(memNo);
		favSprodVO.setProdNo(prodNo);
		
		dao.insert(favSprodVO);	
		return favSprodVO;
	}
	
	
	public void deleteFavSprod(String memNo, String prodNo) {
		dao.delete(memNo, prodNo); 
	}
	
	public Fav_sprodVO getOneFavSprodVO(String memNo, String prodNo) {
		return dao.findByPrimaryKey(memNo, prodNo);
	}
	
	public List<Fav_sprodVO> getAll(){
		return dao.getAll();	
	}
}
