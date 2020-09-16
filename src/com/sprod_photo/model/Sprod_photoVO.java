package com.sprod_photo.model;

import com.mysql.jdbc.Blob;

public class Sprod_photoVO implements java.io.Serializable{
	private String photoNo;
	private String prodNo;
	private byte[] photoContent;
	
	public String getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public byte[] getPhotoContent() {
		return photoContent;
	}
	public void setPhotoContent(byte[] photoCont) {
		this.photoContent = photoCont;
	}
	

	
}
