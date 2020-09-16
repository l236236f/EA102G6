package com.gat_detail.model;

import java.util.List;

public class GatDetailService {
	
	private GatDetailDAO_interface dao;
	
	public GatDetailService() {
		dao = new GatDetailJDBCDAO();
	}
	
	public GatDetailVO addDetail(String gatNo, String memNo) {
		GatDetailVO gatDetailVO = new GatDetailVO();
		gatDetailVO.setGatNo(gatNo);
		gatDetailVO.setMemNo(memNo);
		dao.insert(gatDetailVO);
		return gatDetailVO;
	}
	
	public GatDetailVO deleteDetail(String gatNo, String memNo) {
		GatDetailVO gatDetailVO = new GatDetailVO();
		gatDetailVO.setGatNo(gatNo);
		gatDetailVO.setMemNo(memNo);
		dao.delete(gatDetailVO);
		return gatDetailVO;
	}
	
	public GatDetailVO getOneGat(String memNo) {
		return dao.findByPrimaryKey(memNo);
	}
	
	public List<GatDetailVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		GatDetailService detailSvc = new GatDetailService();
//		detailSvc.addDetail("G004", "M004");
//		System.out.println("新增成功");
//		detailSvc.deleteDetail("G004", "M004");
//		System.out.println("刪除成功");
		GatDetailVO r1 = detailSvc.getOneGat("M001");
		System.out.println("M001追蹤內容: " + r1.getGatNo());
		List<GatDetailVO> rall = detailSvc.getAll();
		for(GatDetailVO result : rall) {
			System.out.println(result.getMemNo() + "追蹤的揪團: " + result.getGatNo());
		}
		
	}

}
