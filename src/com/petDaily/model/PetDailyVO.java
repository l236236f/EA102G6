package com.petDaily.model;

import java.sql.Date;

public class PetDailyVO implements java.io.Serializable{
	private String pdNo;
	private String petNo;
	private String pdClass;
	private String pdCont;
	private Date   editTime;
	
	public String getPdNo() {
		return pdNo;
	}
	public void setPdNo(String pdNo) {
		this.pdNo = pdNo;
	}
	public String getPetNo() {
		return petNo;
	}
	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}
	public String getPdClass() {
		return pdClass;
	}
	public void setPdClass(String pdClass) {
		this.pdClass = pdClass;
	}
	public String getPdCont() {
		return pdCont;
	}
	public void setPdCont(String pdCont) {
		this.pdCont = pdCont;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	
	
}
