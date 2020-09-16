package com.fosmphoto.model;

import java.util.List;

public class FosmPhoService {
	FosmPhotoDAOI fmp = null;
	public FosmPhoService() {
		fmp = new FosmPhotoDAO();
	}
	
	public void addPho(String fosmNo, byte[] phoCon) {
		fmp.insert(fosmNo, phoCon);
	}
	
	public void delPho(String phoNo) {
		fmp.delete(phoNo);
	}
	
	public List<String> getAllByFosm(String fosmNo) {
		return fmp.getAll(fosmNo);
	}
	
	public FosmPhotoVO getPhoto(String phoNo) {
		return fmp.getPhoto(phoNo);
	}
}
