package com.fav_sprod.model;

import java.sql.Date;

public class Fav_sprodVO implements java.io.Serializable{
	private String memNo;
	private String prodNo;
	private Date favTime;
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public Date getFavTime() {
		return favTime;
	}
	public void setFavTime(Date favTime) {
		this.favTime = favTime;
	}
	
}
