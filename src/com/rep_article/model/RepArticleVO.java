package com.rep_article.model;

import java.sql.Timestamp;

public class RepArticleVO {
	
	private String repno;       //檢舉編號
	private String memno;       //會員編號
	private String artno;       //文章編號
	private Timestamp reptime;  //檢舉時間
	private String repreason;   //檢舉原因
	private String repstatus;   //處理檢舉狀態
	
	public String getRepno() {
		return repno;
	}
	public void setRepno(String repno) {
		this.repno = repno;
	}
	
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	public Timestamp getReptime() {
		return reptime;
	}
	public void setReptime(Timestamp reptime) {
		this.reptime = reptime;
	}
	
	public String getRepreason() {
		return repreason;
	}
	public void setRepreason(String repreason) {
		this.repreason = repreason;
	}
	
	public String getRepstatus() {
		return repstatus;
	}
	public void setRepstatus(String repstatus) {
		this.repstatus = repstatus;
	}	

}
