package com.petDaily.model;

import java.util.List;

public class PetDailyService {

	private PetDailyDAO_interface dao;
	
	public PetDailyService() {
		dao = new PetDailyDAO();
	}
	
	public PetDailyVO addPetDaily(String petNo,String pdClass,String pdCont) {
		
		PetDailyVO petDailyVO = new PetDailyVO();
		
		petDailyVO.setPetNo(petNo);
		petDailyVO.setPdClass(pdClass);
		petDailyVO.setPdCont(pdCont);
		
		petDailyVO = dao.insert(petDailyVO);
		return petDailyVO;
	}
	
	public PetDailyVO updatePetDaily(String pdNo,String petNo,String pdClass,String pdCont) {
		
		PetDailyVO petDailyVO = new PetDailyVO();
		
		petDailyVO.setPdNo(pdNo);
		petDailyVO.setPetNo(petNo);
		petDailyVO.setPdClass(pdClass);
		petDailyVO.setPdCont(pdCont);
		
		dao.update(petDailyVO);
		
		return petDailyVO;
	}
	
	public void deletePetDaily(String pdNo) {
		dao.delete(pdNo);
	}
	
	public PetDailyVO getOnePetDaily(String pdNo) {
		return dao.findByPrimaryKey(pdNo);
	}
	
	public List<PetDailyVO> getAll(){
		return dao.getAll();
	}
	
	public List<PetDailyVO> findByPetNo(String petNo){
		return dao.findByPetNo(petNo);
	}
}
