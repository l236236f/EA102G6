package com.sprod_order.model;

import java.sql.Date;

public class Sprod_orderVO implements java.io.Serializable{
	private String orderNo;
	private String memNo;
	private Date orderTime;
	private Integer tranMethod;
	private String tranAdd;
	private String addresseeName;
	private String addresseeMail;
	private Integer orderTotal;
	private Integer orderStatus;
	private String spNo;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getTranMethod() {
		return tranMethod;
	}
	public void setTranMethod(Integer tranMethod) {
		this.tranMethod = tranMethod;
	}
	public String getTranAdd() {
		return tranAdd;
	}
	public void setTranAdd(String tranAdd) {
		this.tranAdd = tranAdd;
	}
	public String getAddresseeName() {
		return addresseeName;
	}
	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}
	public String getAddresseeMail() {
		return addresseeMail;
	}
	public void setAddresseeMail(String addresseeMail) {
		this.addresseeMail = addresseeMail;
	}
	public Integer getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSpNo() {
		return spNo;
	}
	public void setSpNo(String spNo) {
		this.spNo = spNo;
	}
	
}
