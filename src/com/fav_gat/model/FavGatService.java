package com.fav_gat.model;

import java.util.List;

public class FavGatService {
	
	private FavGatDAO_interface dao;
	
	public FavGatService() {
		dao = new FavGatJDBCDAO();
	}
	
	public FavGatVO addFav(String memNo, String gatNo) {
		FavGatVO favGatVO = new FavGatVO();
		favGatVO.setGatNo(gatNo);
		favGatVO.setMemNo(memNo);
		dao.insert(favGatVO);
		return favGatVO;
	}
	
	public FavGatVO deleteFav(String memNo, String gatNo) {
		FavGatVO favGatVO = new FavGatVO();
		favGatVO.setGatNo(gatNo);
		favGatVO.setMemNo(memNo);
		dao.delete(favGatVO);
		return favGatVO;
	}
	
	public FavGatVO getOneGat(String memNo) {
		return dao.findByPrimaryKey(memNo);
	}
	
	public List<FavGatVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		FavGatService favSvc = new FavGatService();
		favSvc.addFav("M004", "G004");
		System.out.println("�s�W���\");
		favSvc.deleteFav("M004", "G004");
		System.out.println("�R�����\");
		FavGatVO r1 = favSvc.getOneGat("M001");
		System.out.println("M001�l�ܤ��e: " + r1.getGatNo());
		List<FavGatVO> rall = favSvc.getAll();
		for(FavGatVO result : rall) {
			System.out.println(result.getMemNo() + "�l�ܪ�����: " + result.getGatNo());
		}
		
	}

}
