package com.pet.model;

import java.sql.Date;
import java.util.List;

import com.mem.model.MemVO;

public class PetService {

	private PetDAO_interface dao;
	
	public PetService() {
		dao = new PetDAO();
	}
	
	public PetVO addPet(String memNo,String petName,byte[] petPhoto,
			String petKind,String petVariety,Date petBirth,String petGender,
			String petID,String petIntro) {
		
		PetVO petVO = new PetVO();
		
		petVO.setMemNo(memNo);
		petVO.setPetName(petName);
		petVO.setPetPhoto(petPhoto);
		petVO.setPetKind(petKind);
		petVO.setPetVariety(petVariety);
		petVO.setPetBirth(petBirth);
		petVO.setPetGender(petGender);
		petVO.setPetID(petID);
		petVO.setPetIntro(petIntro);
		
		dao.insert(petVO);
		
		return petVO;
	}
	
	public PetVO updatePet(String petNo, String memNo,String petName,byte[] petPhoto,
			String petKind,String petVariety,Date petBirth,String petGender,
			String petIntro) {
		
		PetVO petVO = new PetVO();
		
		petVO.setPetNo(petNo);
		petVO.setMemNo(memNo);
		petVO.setPetName(petName);
		petVO.setPetPhoto(petPhoto);
		petVO.setPetKind(petKind);
		petVO.setPetVariety(petVariety);
		petVO.setPetBirth(petBirth);
		petVO.setPetGender(petGender);
		petVO.setPetIntro(petIntro);
		
		dao.update(petVO);
		
		return petVO;
	}
	
	public void deletePet(String petNo) {
		dao.delete(petNo);
	}
	
	public PetVO getOnePet(String petNo) {
		return dao.findByPrimaryKey(petNo);
	}
	
	public List<PetVO> getAll(){
		return dao.getAll();
	}
	
	public List<PetVO> findByMemNo(String memNo){
		return dao.findByMemNo(memNo);
	}
	
}
