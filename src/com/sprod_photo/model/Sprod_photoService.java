package com.sprod_photo.model;

import java.util.List;

import com.shop_product.model.Shop_productVO;

public class Sprod_photoService {
	private Sprod_photoDAO_interface dao;
	
	public Sprod_photoService() {
//		dao = new Sprod_photoDAO();
		dao = new Sprod_photoJNDIDAO();
	}
	
	public Sprod_photoVO addSprodPhoto(String prodNo, byte[] photoContent) {

		Sprod_photoVO sprodPhotoVO = new Sprod_photoVO();	
		sprodPhotoVO.setProdNo(prodNo);
		sprodPhotoVO.setPhotoContent(photoContent);

		dao.insert(sprodPhotoVO);	
		return sprodPhotoVO;
	}
	
	public Sprod_photoVO updateSprodPhoto(String prodNo, byte[] photoContent, String photoPhotoNo) {

		Sprod_photoVO sprodPhotoVO = new Sprod_photoVO();	
		sprodPhotoVO.setProdNo(prodNo);
		sprodPhotoVO.setPhotoContent(photoContent);
		sprodPhotoVO.setPhotoNo(photoPhotoNo);

		dao.insert(sprodPhotoVO);	
		return sprodPhotoVO;
	}
	
	
	public void deleteSprodPhoto(String photoNo) {
		dao.delete(photoNo); 
	}
	
	public Sprod_photoVO getOneSprodPhotoVO(String photoNo) {
		return dao.findByPrimaryKey(photoNo);
	}
	
	public List<Sprod_photoVO> getAll(){
		return dao.getAll();	
	}
	
	public List<Sprod_photoVO> getAll_by_prod_no(String prodNo){
		return dao.getAll_by_prod_no(prodNo);
	}
	
	public List<byte[]> getPhotos(String pordNo){
		return dao.getPhotos(pordNo);
	}
	
	public List<String> getPhotoNos(String prodNo){
		return dao.getPhotoNos(prodNo);
	}

	public List<byte[]> getImages(String prodNo) {
		return dao.getImages(prodNo);
	}

	
}
