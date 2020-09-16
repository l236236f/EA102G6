package com.foscare.model;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.pet.model.PetVO;

public class FosterService {
	private FosterDAOI dao;

	public FosterService() {
		dao = new FosterDAO();
	}

	public FosterVO addFoscare(String memNo, String petNo, Date startTime, Date endTime, String fosnrun, String fosSize,
			String fosType, String fosRemark, Integer fosMoney) {
		FosterVO fos = new FosterVO();
		fos.setMemNo(memNo);
		fos.setPetNo(petNo);
		fos.setFosStartTime(startTime);
		fos.setFosEndTime(endTime);
		fos.setFosnrun(fosnrun);
		fos.setFosSize(fosSize);
		fos.setFosType(fosType);
		fos.setFosRemark(fosRemark);
		fos.setFosMoney(fosMoney);
		dao.insert(fos);
		return fos;
	}

	public void updateFoscare(FosterVO fosterVO) {
		dao.update(fosterVO);
	}

	public FosterVO getOneFoscare(String fosNo) {
		return dao.findByPrimaryKey(fosNo);
	}

	public List<FosterVO> getAll() {
		return dao.getAll();
	}

	public List<FosterVO> getAllByMemNo(String memNo) {
		return dao.getAllByMemNo(memNo);
	}

	public List<FosterVO> getAllByFosmNo(String fosmNo) {
		return dao.getAllByFosmNo(fosmNo);
	}

	public void addSign(byte[] sign, String fosNo, String AorB) throws IOException {
		dao.updateSign(sign, fosNo, AorB);
	}

	public void changeStatus(String fosNo, String fosStatus) {
		dao.updateStatus(fosNo, fosStatus);
	}

	public void addevaluation(String fosNo, Integer i, String eva) {
		dao.evaluation(fosNo, i, eva);
	}

	public List<PetVO> getPetNames(String memNo) {
		return dao.findPetByMemNo(memNo);
	}
	
	public void addFosmEvares(String fosNo,String fosmEvares) {
		dao.updateFosmres(fosNo, fosmEvares);
	}
	
	public void setTimeOutFos() {
		dao.getAlltimeUp();
	}
}
