package com.dailyPhoto.model;

import java.io.Serializable;

public class DailyPhotoVO implements Serializable{
	private String dphNo;
	private String pdNo;
	private byte[] photo;
	
	public String getDphNo() {
		return dphNo;
	}
	public void setDphNo(String dphNo) {
		this.dphNo = dphNo;
	}
	public String getPdNo() {
		return pdNo;
	}
	public void setPdNo(String pdNo) {
		this.pdNo = pdNo;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}
