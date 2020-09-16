package com.rep_gat.model;

import java.util.List;

public class RepGatService {

	private RepGatDAO_interface dao;

	public RepGatService() {
		dao = new RepGatJDBCDAO();
	}

	public RepGatVO addRep(String gatNo, String memNo, String repCont) {
		RepGatVO repGatVO = new RepGatVO();		
		repGatVO.setGatNo(gatNo);
		repGatVO.setMemNo(memNo);
		repGatVO.setRepCont(repCont);
		dao.insert(repGatVO);
		return repGatVO;		
	}

	public RepGatVO updateRep(String repNo, String repCont) {
		RepGatVO repGatVO = new RepGatVO();
		repGatVO.setRepNo(repNo);
		repGatVO.setRepCont(repCont);
		dao.update(repGatVO);
		return repGatVO;
	}

	public void deleteRep(String repNo) {
		dao.delete(repNo);
	}
	
	public RepGatVO getOneGat(String repNo) {
		return dao.findByPrimaryKey(repNo);
	}
	
	public List<RepGatVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		RepGatService repSvc = new RepGatService();
		repSvc.addRep("G001", "M002", "BBBBB");
		System.out.println("新增成功");
		repSvc.updateRep("R007", "AAA");
		System.out.println("修改成功");
		repSvc.deleteRep("R007");
		System.out.println("刪除成功");
		RepGatVO r1 = repSvc.getOneGat("R001");
		System.out.println("R001內容: " + r1.getRepCont());
		List<RepGatVO> rall = repSvc.getAll();
		for(RepGatVO result : rall) {
			System.out.println(result.getRepNo() + "的內容: " + result.getRepCont());
		}
		
	}
	//檢舉審核
	public void update_approved(String repno) {
		dao.update_approved(repno);
	}
	public void update_notapproved(String repno) {
		dao.update_notapproved(repno);
	}
	
}
