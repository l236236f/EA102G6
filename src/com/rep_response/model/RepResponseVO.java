package com.rep_response.model;

import java.sql.Timestamp;

public class RepResponseVO {
	
	private String repno;       //檢舉編號
	private String memno;       //會員編號
	private String resno;       //回覆編號
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
	
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
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
