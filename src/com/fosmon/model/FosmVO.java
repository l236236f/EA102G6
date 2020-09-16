package com.fosmon.model;

import java.io.Serializable;

public class FosmVO implements Serializable{
	private String fosmNo;
	private String memNo;
	private String fosmPetSize;
	private String fosmPetType;
	private String fosmnrun;
	private String fosmContain;
	private Integer fosStar;
	private Integer fosmEvacount;
	private Double fosmLat;
	private Double fosmLng;
	
	public Double getFosmLat() {
		return fosmLat;
	}
	public void setFosmLat(Double fosmLat) {
		this.fosmLat = fosmLat;
	}
	public Double getFosmLng() {
		return fosmLng;
	}
	public void setFosmLng(Double fosmLng) {
		this.fosmLng = fosmLng;
	}
	public String getFosmNo() {
		return fosmNo;
	}
	public void setFosmNo(String fosmNo) {
		this.fosmNo = fosmNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getFosmPetSize() {
		return fosmPetSize;
	}
	public void setFosmPetSize(String fosmPetSize) {
		this.fosmPetSize = fosmPetSize;
	}
	public String getFosmPetType() {
		return fosmPetType;
	}
	public void setFosmPetType(String fosmPetType) {
		this.fosmPetType = fosmPetType;
	}
	public String getFosmnrun() {
		return fosmnrun;
	}
	public void setFosmnrun(String fosmnrun) {
		this.fosmnrun = fosmnrun;
	}
	public String getFosmContain() {
		return fosmContain;
	}
	public void setFosmContain(String fosmContain) {
		this.fosmContain = fosmContain;
	}
	public Integer getFosStar() {
		return fosStar;
	}
	public void setFosStar(Integer fosStar) {
		this.fosStar = fosStar;
	}
	public Integer getFosmEvacount() {
		return fosmEvacount;
	}
	public void setFosmEvacount(Integer fosmEvacount) {
		this.fosmEvacount = fosmEvacount;
	}
}
