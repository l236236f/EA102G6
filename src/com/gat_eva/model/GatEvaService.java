package com.gat_eva.model;

import java.util.List;

public class GatEvaService {

	private GatEvaDAO_interface dao;

	public GatEvaService() {
		dao = new GatEvaJDBCDAO();
	}
	
	public GatEvaVO addEva(String memNo, String gatNo, Double gatEva) {
		GatEvaVO gatEvaVO = new GatEvaVO();
		gatEvaVO.setGatNo(gatNo);
		gatEvaVO.setMemNo(memNo);
		gatEvaVO.setGatEva(gatEva);
		dao.insert(gatEvaVO);
		return gatEvaVO;
	}
	
	public GatEvaVO updateEva(String memNo, String gatNo, Double gatEva) {
		GatEvaVO gatEvaVO = new GatEvaVO();
		gatEvaVO.setGatNo(gatNo);
		gatEvaVO.setMemNo(memNo);
		gatEvaVO.setGatEva(gatEva);
		dao.update(gatEvaVO);
		return gatEvaVO;
	}
	
	public GatEvaVO getOneGat(String memNo, String gatNo) {
		return dao.findByPrimaryKey(memNo, gatNo);
	}
	
	public List<GatEvaVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		GatEvaService evaSvc = new GatEvaService();
//		evaSvc.addEva("M003", "G006", 3.5);
//		System.out.println("新增成功");
//		evaSvc.updateEva("M003", "G006", 4.5);
//		System.out.println("修改成功");
//		GatEvaVO r1 = evaSvc.getOneGat("M001", "G011");
//		System.out.println(r1.getMemNo() + "對" + r1.getGatNo() + "揪團的分數: " + r1.getGatEva());
//		List<GatEvaVO> rall = evaSvc.getAll();
//		for(GatEvaVO result : rall) {
//			System.out.println(result.getMemNo() + "對" + result.getGatNo() + "揪團的分數: " + result.getGatEva());		}

	}
}
