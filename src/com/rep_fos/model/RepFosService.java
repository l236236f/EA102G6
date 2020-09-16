package com.rep_fos.model;

import java.util.List;

public class RepFosService {
	RepFosDAOI dao;
	public RepFosService() {
		dao = new RepFosDAO();
	}
	
	public void addFosRep(String fosNo, String memNo, String repCont) {
		RepFosVO repfosVO = new RepFosVO();
		repfosVO.setFosNo(fosNo);
		repfosVO.setMemNo(memNo);
		repfosVO.setRepCont(repCont);
		dao.insert(repfosVO);
	}
	public void updateFosRep(RepFosVO repfosVO) {
		dao.update(repfosVO);
	}
	public List<RepFosVO> getAllBymemNo(String memNo){
		return dao.findBymemNo(memNo);
	}
	public List<RepFosVO> getAll() {
		return dao.getAll();
	}
	public RepFosVO getOneRep(String repNo) {
		return dao.findByPrimaryKey(repNo);
	}
	
	//¿À¡|ºfÆ÷
	public void update_approved(String repNo) {
		dao.update_approved(repNo);
	}
	public void update_notapproved(String repNo) {
		dao.update_notapproved(repNo);
	}
	
}
