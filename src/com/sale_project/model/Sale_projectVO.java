package com.sale_project.model;

import java.sql.Date;

public class Sale_projectVO implements java.io.Serializable{
	private String spNo;
	private String venNo;
	private String spName;
	private Integer spQuan;
	private Integer spTotPrice;
	private Integer spTotOff;
	private Date spStartTime;
	private Date spEndTime;
	private Integer spStatus;
	public String getSpNo() {
		return spNo;
	}
	public void setSpNo(String spNo) {
		this.spNo = spNo;
	}
	public String getVenNo() {
		return venNo;
	}
	public void setVenNo(String venNo) {
		this.venNo = venNo;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Integer getSpQuan() {
		return spQuan;
	}
	public void setSpQuan(Integer spQuan) {
		this.spQuan = spQuan;
	}
	public Integer getSpTotPrice() {
		return spTotPrice;
	}
	public void setSpTotPrice(Integer spTotPrice) {
		this.spTotPrice = spTotPrice;
	}
	public Integer getSpTotOff() {
		return spTotOff;
	}
	public void setSpTotOff(Integer spTotOff) {
		this.spTotOff = spTotOff;
	}
	public Date getSpStartTime() {
		return spStartTime;
	}
	public void setSpStartTime(Date spStartTime) {
		this.spStartTime = spStartTime;
	}
	public Date getSpEndTime() {
		return spEndTime;
	}
	public void setSpEndTime(Date spEndTime) {
		this.spEndTime = spEndTime;
	}
	public Integer getSpStatus() {
		return spStatus;
	}
	public void setSpStatus(Integer spStatus) {
		this.spStatus = spStatus;
	}
	
}
