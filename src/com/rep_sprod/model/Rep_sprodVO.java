package com.rep_sprod.model;

import java.sql.Date;

public class Rep_sprodVO implements java.io.Serializable{
	private String repNo;
	private String memNo;
	private String prodNo;
	private Date repTime;
	private String repReason;
	private Integer repStatus;
	
	public String getRepNo() {
		return repNo;
	}
	public void setRepNo(String repNo) {
		this.repNo = repNo;
	}
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
	public Date getRepTime() {
		return repTime;
	}
	public void setRepTime(Date repTime) {
		this.repTime = repTime;
	}
	public String getRepReason() {
		return repReason;
	}
	public void setRepReason(String repReason) {
		this.repReason = repReason;
	}
	public Integer getRepStatus() {
		return repStatus;
	}
	public void setRepStatus(Integer repStatus) {
		this.repStatus = repStatus;
	}
	
	
}
