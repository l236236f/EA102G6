package com.fosmon.model;

import java.util.List;

public class FosmService {
	FosmDAOI dao;
	
	public FosmService() {
		dao= new FosmDAO();
	}
	
	public void addFosMon(FosmVO fosmVO) {
		dao.insert(fosmVO);
	}
	
	public void updateFosMon(FosmVO fosmVO) {
		dao.update(fosmVO);
	}
	
	public FosmVO getOneFosm(String memNo) {
		return dao.findByMemNo(memNo);
	}
	
	public FosmVO getOneByFosmNo(String fosmNo) {
		return dao.findByFosmNo(fosmNo);
	}
	
	public List<FosmVO> getAll(){
		return dao.getAll();
	}
	
	public void changeStar(FosmVO fosmVO) {
		dao.updateStar(fosmVO);
	}
}
