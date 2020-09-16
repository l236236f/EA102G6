package com.art_response.model;

import java.sql.Timestamp;

public class ArtResponseVO {
	
	private String resno;          //回覆編號
	private String artno;          //文章編號
	private String memno;          //會員編號
	private String rescontent;     //回覆內容
	private Timestamp restime;     //發布時間
	private String resstatus;      //回覆狀態
	
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	
	public String getRescontent() {
		return rescontent;
	}
	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}
	
	public Timestamp getRestime() {
		return restime;
	}
	public void setRestime(Timestamp restime) {
		this.restime = restime;
	}
	
	public String getResstatus() {
		return resstatus;
	}
	public void setResstatus(String resstatus) {
		this.resstatus = resstatus;
	}

}
