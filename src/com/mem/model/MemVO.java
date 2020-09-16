package com.mem.model;

import java.io.Serializable;
import java.sql.Date;

public class MemVO implements Serializable{
	private String memNo;
	private String memAcc;
	private String memPw;	
	private String memName;	
	private Date   memBirth;	
	private String memID;	
	private String memTel;	
	private String memGender;	
	private String memAddr;	
	private String memEmail;	
	private String memMoney;	
	private byte[] memPhoto;	
	private String memIntro;
	private int	   bonus;
	private Date   regTime;
	private String memStatus;
	private String mom;
	private int    uppodEvas;
	private int    uppodEvacount;
	private int    gatEvas;
	private int    gatEvacount;
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memId) {
		this.memID = memId;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemMoney() {
		return memMoney;
	}
	public void setMemMoney(String memMoney) {
		this.memMoney = memMoney;
	}
	public byte[] getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(byte[] memPhoto) {
		this.memPhoto = memPhoto;
	}
	public String getMemIntro() {
		return memIntro;
	}
	public void setMemIntro(String memIntro) {
		this.memIntro = memIntro;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
	public String getMom() {
		return mom;
	}
	public void setMom(String mom) {
		this.mom = mom;
	}
	public int getUppodEvas() {
		return uppodEvas;
	}
	public void setUppodEvas(int uppodEvas) {
		this.uppodEvas = uppodEvas;
	}
	public int getUppodEvacount() {
		return uppodEvacount;
	}
	public void setUppodEvacount(int uppodEvacount) {
		this.uppodEvacount = uppodEvacount;
	}
	public int getGatEvas() {
		return gatEvas;
	}
	public void setGatEvas(int gatEvas) {
		this.gatEvas = gatEvas;
	}
	public int getGatEvacount() {
		return gatEvacount;
	}
	public void setGatEvacount(int gatEvacount) {
		this.gatEvacount = gatEvacount;
	}
	
	
}
