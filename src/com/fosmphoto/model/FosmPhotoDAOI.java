package com.fosmphoto.model;

import java.util.List;

public interface FosmPhotoDAOI {
	public void insert(String fosmNo,byte[] phoCon);
	
	public void delete(String phoNo);
	
	//照片編號回傳單一照片VO
	public FosmPhotoVO getPhoto(String phoNo);
			
	//用保母編號找全部照片的編號
	public List<String> getAll(String fosmNo);

	
}
