package com.dailyPhoto.model;

import java.util.List;

import com.petDaily.model.PetDailyVO;

public class DailyPhotoService {

	private DailyPhotoDAO_interface dao;

	public DailyPhotoService() {
		dao = new DailyPhotoDAO();
	}

	
	public DailyPhotoVO addDailyPhoto(String pdNo,byte[] photo) {

		DailyPhotoVO dailyPhotoVO = new DailyPhotoVO();

		dailyPhotoVO.setPdNo(pdNo);
		dailyPhotoVO.setPhoto(photo);
		
		dao.insert(dailyPhotoVO);

		return dailyPhotoVO;
	}
	

	public DailyPhotoVO updateDailyPhoto(String dphNo,String pdNo,byte[] photo) {

		DailyPhotoVO dailyPhotoVO = new DailyPhotoVO();

		dailyPhotoVO.setDphNo(dphNo);
		dailyPhotoVO.setPdNo(pdNo);
		dailyPhotoVO.setPhoto(photo);
		
		dao.update(dailyPhotoVO);
		
		return dailyPhotoVO;
	}

	public void deleteDailyPhoto(String dphNo) {
		dao.delete(dphNo);
	}

	public DailyPhotoVO getOneDailyPhoto(String dphNo) {
		return dao.findByPrimaryKey(dphNo);
	}

	public List<DailyPhotoVO> getAll() {
		return dao.getAll();
	}
	
	public List<DailyPhotoVO> findByPDNo(String pdNo){
		return dao.findByPDNo(pdNo);
	}
	public void deleteByPDNo(String pdNo){
		dao.deleteByPDNo(pdNo);
	}
}
