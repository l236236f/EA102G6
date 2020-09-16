package com.gat.model;

import java.sql.*;
import java.util.List;

public class GatService {

	private GatDAO_interface dao;

	public GatService() {
		dao = new GatJDBCDAO();
	}

	public GatVO addGat(String memNo, String gatName, Date gatTime, String gatLoc, String gatIntro, Date gatStarttime,
			Date gatEndtime, Integer gatMax, Integer gatMin, byte[] gatPhoto, String gatType, Double gatLat, Double gatLng) {

		GatVO gatVO = new GatVO();

		gatVO.setMemNo(memNo);
		gatVO.setGatName(gatName);
		gatVO.setGatTime(gatTime);
		gatVO.setGatLoc(gatLoc);
		gatVO.setGatIntro(gatIntro);
		gatVO.setGatStarttime(gatStarttime);
		gatVO.setGatEndtime(gatEndtime);
		gatVO.setGatMax(gatMax);
		gatVO.setGatMin(gatMin);
		gatVO.setGatPhoto(gatPhoto);
		gatVO.setGatType(gatType);
		gatVO.setGatLat(gatLat);
		gatVO.setGatLng(gatLng);
//		String gatNo = null; 
		dao.insert(gatVO);
//		gatVO.setGatNo(gatNo);
		return gatVO;
	}

	public GatVO updateGat(String gatNo, String memNo, String gatName, Date gatTime, String gatLoc, String gatIntro,
			Date gatStarttime, Date gatEndtime, Integer gatMax, Integer gatMin, byte[] gatPhoto, String gatType, Double gatLat, Double gatLng) {

		GatVO gatVO = new GatVO();

		gatVO.setMemNo(memNo);
		gatVO.setGatNo(gatNo);
		gatVO.setGatName(gatName);
		gatVO.setGatTime(gatTime);
		gatVO.setGatLoc(gatLoc);
		gatVO.setGatIntro(gatIntro);
		gatVO.setGatStarttime(gatStarttime);
		gatVO.setGatEndtime(gatEndtime);
		gatVO.setGatMax(gatMax);
		gatVO.setGatMin(gatMin);
		gatVO.setGatPhoto(gatPhoto);
		gatVO.setGatType(gatType);
		gatVO.setGatLat(gatLat);
		gatVO.setGatLng(gatLng);
		dao.update(gatVO);
		return gatVO;
	}

	public void updateStatus(String gatStatus, String gatNo) {
		dao.updateStatus(gatStatus, gatNo);
	}

	public void deleteGat(String gatNo) {
		dao.delete(gatNo);
	}

	public GatVO getOneGat(String gatNo) {
		return dao.findByPrimaryKey(gatNo);
	}

	public List<GatVO> getAllForUpdateList() {
		return dao.getAllForUpdateList();
	}
	public List<GatVO> getAll() {
		return dao.getAll();
	}
	public List<GatVO> getAllByEndtime() {
		return dao.getAllByEndtime();
	}
	public List<GatVO> getAllLike(String gatName) {
		return dao.getAllLike(gatName);
	}
	public void autoStatus() {
		dao.autoStatus();
	}
	
	public void update_report(String gatNo) {
		dao.update_report(gatNo);
	}

	public static void main(String[] args) {
		GatService gatSvc= new GatService();
//		
//		Date gatTime = Date.valueOf("2020-08-31");
//		Date gatStarttime = Date.valueOf("2020-07-28");
//		Date gatEndtime = Date.valueOf("2020-08-15");
//
//		gatSvc.deleteGat("G010");
//		System.out.println("AAAA");
		
		List<GatVO> list = gatSvc.getAllLike("·|");
		for(GatVO result: list) {
			System.out.println(result.getGatName());
			System.out.println(result.getGatLat());
			System.out.println(result.getGatLng());
		}
		System.out.println(list.size());
	}

};
