package com.rep_sprod.model;

import java.util.List;

public class Rep_sprodService {
	private Rep_sprodDAO_interface dao;
	
	public Rep_sprodService() {
		dao = new Rep_sprodDAO();
	}
	
	public Rep_sprodVO addRepSprod(String memNo, String prodNo, 
			String repReason, Integer repStatus) {
		Rep_sprodVO repSprodVO = new Rep_sprodVO();
		repSprodVO.setMemNo(memNo);
		repSprodVO.setProdNo(prodNo);
		repSprodVO.setRepReason(repReason);
		repSprodVO.setRepStatus(repStatus);
		dao.insert(repSprodVO);
		return repSprodVO;
	}
	
	
	public Rep_sprodVO updateRepSpord(String repNo, Integer repStatus) {
		Rep_sprodVO repSprodVO = new Rep_sprodVO();
		repSprodVO.setRepNo(repNo);
		repSprodVO.setRepStatus(repStatus);
		dao.update(repSprodVO);
		return repSprodVO;
	}
	
	public void deleteRepSprod(String repNo) {
		dao.delete(repNo);
	}
	
	public Rep_sprodVO getOneRepSprodVO(String repNo) {
		return dao.findByPrimaryKey(repNo);
	}
	
	public List<Rep_sprodVO> getAll(){
		return dao.getAll();
	}
}
