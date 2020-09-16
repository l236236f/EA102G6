package com.vendor.model;

import java.io.Serializable;
import java.sql.Date;

public class VendorVO implements Serializable{
	private String venNo;
	private String venAcc;
	private String venPw;	
	private String venName;		
	private String venTel;	
	private String venID;	
	private String venMoney;	
	private String venAddr;	
	private String venEmail;	
	private byte[] venPhoto;	
	private String venIntro;
	private Date   regTime;
	private String venStatus;
	
	public String getVenNo() {
		return venNo;
	}
	public void setVenNo(String venNo) {
		this.venNo = venNo;
	}
	public String getVenAcc() {
		return venAcc;
	}
	public void setVenAcc(String venAcc) {
		this.venAcc = venAcc;
	}
	public String getVenPw() {
		return venPw;
	}
	public void setVenPw(String venPw) {
		this.venPw = venPw;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getVenTel() {
		return venTel;
	}
	public void setVenTel(String venTel) {
		this.venTel = venTel;
	}
	public String getVenID() {
		return venID;
	}
	public void setVenID(String venID) {
		this.venID = venID;
	}
	public String getVenMoney() {
		return venMoney;
	}
	public void setVenMoney(String venMoney) {
		this.venMoney = venMoney;
	}
	public String getVenAddr() {
		return venAddr;
	}
	public void setVenAddr(String venAddr) {
		this.venAddr = venAddr;
	}
	public String getVenEmail() {
		return venEmail;
	}
	public void setVenEmail(String venEmail) {
		this.venEmail = venEmail;
	}
	public byte[] getVenPhoto() {
		return venPhoto;
	}
	public void setVenPhoto(byte[] venPhoto) {
		this.venPhoto = venPhoto;
	}
	public String getVenIntro() {
		return venIntro;
	}
	public void setVenIntro(String venIntro) {
		this.venIntro = venIntro;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getVenStatus() {
		return venStatus;
	}
	public void setVenStatus(String venStatus) {
		this.venStatus = venStatus;
	}
	
	
}
