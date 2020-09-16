package com.dailyPhoto.model;

import java.util.List;

public interface DailyPhotoDAO_interface {
	public void insert(DailyPhotoVO dailyPhotoVO);
	public void update (DailyPhotoVO dailyPhotoVO);
	public void delete(String dphNo);
	public DailyPhotoVO findByPrimaryKey(String dphNo);
	public List<DailyPhotoVO> getAll();
	public List<DailyPhotoVO> findByPDNo(String pdNo);
	public void deleteByPDNo(String pdNo);
}
