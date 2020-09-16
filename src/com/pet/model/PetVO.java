package com.pet.model;

import java.sql.Date;

public class PetVO implements java.io.Serializable{
	private String petNo;
	private String memNo;
	private String petName;
	private byte[] petPhoto;
	private String petKind;
	private String petVariety;
	private Date   petBirth;
	private String petGender;
	private String petID;
	private String petIntro;
	private Date   regTime;
	private String petStatus;
	
	public String getPetNo() {
		return petNo;
	}
	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public byte[] getPetPhoto() {
		return petPhoto;
	}
	public void setPetPhoto(byte[] petPhoto) {
		this.petPhoto = petPhoto;
	}
	public String getPetKind() {
		return petKind;
	}
	public void setPetKind(String petKind) {
		this.petKind = petKind;
	}
	public String getPetVariety() {
		return petVariety;
	}
	public void setPetVariety(String petVariety) {
		this.petVariety = petVariety;
	}
	public Date getPetBirth() {
		return petBirth;
	}
	public void setPetBirth(Date petBirth) {
		this.petBirth = petBirth;
	}
	public String getPetGender() {
		return petGender;
	}
	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}
	public String getPetID() {
		return petID;
	}
	public void setPetID(String petID) {
		this.petID = petID;
	}
	public String getPetIntro() {
		return petIntro;
	}
	public void setPetIntro(String petIntro) {
		this.petIntro = petIntro;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getPetStatus() {
		return petStatus;
	}
	public void setPetStatus(String petStatus) {
		this.petStatus = petStatus;
	}
	
	
}
