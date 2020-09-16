package com.rep_response.model;

import java.sql.Timestamp;
import java.util.List;

public class RepResponseService {
	
	private RepResponseDAO_interface dao;
	
	public RepResponseService() {
		dao = new RepResponseDAO();
//		dao = new RepResponseJDBCDAO();
	}
	
	public RepResponseVO addRepResponse(String memno, String resno, String repreason) {
		
		RepResponseVO repResponseVO = new RepResponseVO();

		repResponseVO.setMemno(memno);
		repResponseVO.setResno(resno);
		repResponseVO.setRepreason(repreason);
		dao.insert(repResponseVO);		
		
		return repResponseVO;
	}
	
	public RepResponseVO updateRepResponse(String repno, String memno, String resno, 
										   Timestamp reptime, String repreason, String repstatus) {
		
		RepResponseVO repResponseVO = new RepResponseVO();

		repResponseVO.setRepno(repno);
		repResponseVO.setMemno(memno);
		repResponseVO.setResno(resno);
		repResponseVO.setReptime(reptime);
		repResponseVO.setRepreason(repreason);
		repResponseVO.setRepstatus(repstatus);
		dao.update(repResponseVO);		
		
		return repResponseVO;
	}
	
	public void deleteRepResponse(String repno) {
		dao.delete(repno);
	}

	public RepResponseVO getOneRepResponse(String repno) {
		return dao.findByPrimaryKey(repno);
	}

	public List<RepResponseVO> getAll() {
		return dao.getAll();
	}
	public void update_approved(String repno) {
		dao.update_approved(repno);
	}
	public void update_notapproved(String repno) {
		dao.update_notapproved(repno);
	}

}
