package com.sprod_photo.model;

import java.util.*;



public interface Sprod_photoDAO_interface {
	public void insert(Sprod_photoVO sprodPhotoVO);
	public void update(Sprod_photoVO sprodPhotoVO);
	public void delete(String photoNo);
	public Sprod_photoVO findByPrimaryKey(String photoNo);
	public List<Sprod_photoVO> getAll();
	public List<Sprod_photoVO> getAll_by_prod_no(String prodNo);
	public List<byte[]> getPhotos(String prodNo);
	public List<String> getPhotoNos(String prod_no);
	public List<byte[]> getImages(String prodNo);
}
