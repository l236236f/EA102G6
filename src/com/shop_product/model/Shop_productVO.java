package com.shop_product.model;

import java.sql.Date;

public class Shop_productVO implements java.io.Serializable{
	private String prodNo;
	private String venNo;
	private String className;
	private String prodName;
	private String prodIntro;
	private Date increaseTime;
	private Integer price;
	private Integer sprodStatus;
	private Integer evCount;
	private Integer evTotal;
	private byte[] photo;
	
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getVenNo() {
		return venNo;
	}
	public void setVenNo(String venNo) {
		this.venNo = venNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdIntro() {
		return prodIntro;
	}
	public void setProdIntro(String prodIntro) {
		this.prodIntro = prodIntro;
	}
	public Date getIncreaseTime() {
		return increaseTime;
	}
	public void setIncreaseTime(Date increaseTime) {
		this.increaseTime = increaseTime;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSprodStatus() {
		return sprodStatus;
	}
	public void setSprodStatus(Integer sprodStatus) {
		this.sprodStatus = sprodStatus;
	}
	public Integer getEvCount() {
		return evCount;
	}
	public void setEvCount(Integer evCount) {
		this.evCount = evCount;
	}
	public Integer getEvTotal() {
		return evTotal;
	}
	public void setEvTotal(Integer evTotal) {
		this.evTotal = evTotal;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
