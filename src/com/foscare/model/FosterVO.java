package com.foscare.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class FosterVO implements Serializable{

	private String fosNo;
	private String memNo;
	private String petNo;
	private String fosmNo;
	private Date fosStartTime;
	private Date fosEndTime;
	private String fosnrun;
	private String fosSize;
	private String fosType;
	private byte[] fosSignA;
	private byte[] fosSignB;
	private Integer fosMoney;
	private String fosRemark;
	private String fosStatus;
	private Timestamp fosTime;
	private Double fosmEvas;
	private String fosmEvacon;
	private String fosmEvares;
	
	public String getFosmEvares() {
		return fosmEvares;
	}
	public void setFosmEvares(String fosmEvares) {
		this.fosmEvares = fosmEvares;
	}
	public Double getFosmEvas() {
		return fosmEvas;
	}
	public void setFosmEvas(Double fosmEvas) {
		this.fosmEvas = fosmEvas;
	}
	
	public String getFosmEvacon() {
		return fosmEvacon;
	}
	public void setFosmEvacon(String fosmEvacon) {
		this.fosmEvacon = fosmEvacon;
	}
	
	
	public Timestamp getFosTime() {
		return fosTime;
	}
	public void setFosTime(Timestamp fosTime) {
		this.fosTime = fosTime;
	}
	
	
	public String getFosStatus() {
		return fosStatus;
	}
	public void setFosStatus(String fosStatus) {
		this.fosStatus = fosStatus;
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
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getPetNo() {
		return petNo;
	}
	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}
	public String getFosmNo() {
		return fosmNo;
	}
	public void setFosmNo(String fosmNo) {
		this.fosmNo = fosmNo;
	}
	public Date getFosStartTime() {
		return fosStartTime;
	}
	public void setFosStartTime(Date fosStartDay) {
		this.fosStartTime = fosStartDay;
	}
	public Date getFosEndTime() {
		return fosEndTime;
	}
	public void setFosEndTime(Date fosEndDay) {
		this.fosEndTime = fosEndDay;
	}
	public String getFosnrun() {
		return fosnrun;
	}
	public void setFosnrun(String fosnrun) {
		this.fosnrun = fosnrun;
	}
	public byte[] getFosSignA() {
		return fosSignA;
	}
	public void setFosSignA(byte[] fosSignA) {
		this.fosSignA = fosSignA;
	}
	public byte[] getFosSignB() {
		return fosSignB;
	}
	public void setFosSignB(byte[] fosSignB) {
		this.fosSignB = fosSignB;
	}
	public String getFosSize() {
		return fosSize;
	}
	public void setFosSize(String fosSize) {
		this.fosSize = fosSize;
	}
	public String getFosType() {
		return fosType;
	}
	public void setFosType(String fosType) {
		this.fosType = fosType;
	}
	public Integer getFosMoney() {
		return fosMoney;
	}
	public void setFosMoney(Integer fosMoney) {
		this.fosMoney = fosMoney;
	}
	public String getFosRemark() {
		return fosRemark;
	}
	public void setFosRemark(String fosRemark) {
		this.fosRemark = fosRemark;
	}
	
	
}
