package com.rep_fos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RepFosVO implements Serializable{
	private String repNo;
	private String fosNo;
	private String memNo;
	private String repCont;
	private Timestamp repTime;
	private String repStatus;
	public String getRepNo() {
		return repNo;
	}
	public void setRepNo(String repNo) {
		this.repNo = repNo;
	}
	public String getFosNo() {
		return fosNo;
	}
	public void setFosNo(String fosNo) {
		this.fosNo = fosNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String menNo) {
		this.memNo = menNo;
	}
	public String getRepCont() {
		return repCont;
	}
	public void setRepCont(String repCont) {
		this.repCont = repCont;
	}
	public Timestamp getRepTime() {
		return repTime;
	}
	public void setRepTime(Timestamp repTime) {
		this.repTime = repTime;
	}
	public String getRepStatus() {
		return repStatus;
	}
	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}
}
