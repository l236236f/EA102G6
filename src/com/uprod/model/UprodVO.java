package com.uprod.model;
import java.sql.*;

public class UprodVO implements java.io.Serializable{
	private String ProdNo;
	private String SellerNo;
	private String CustNo;
	private String ProdName;
	private String ProdIntro;
	private Integer Price;
	private Timestamp IncreaseTime;
	private String ProdStatus;
	private String OrderStatus;
	private String ReceiveStatus;
	private Timestamp OrderTime;
	private String TranMethod;
	private String TranAddr;
	private String ProdType;
	private Integer EvaStar;
	private byte[] Photo;
	
	public String getProdNo() {
		return ProdNo;
	}
	public void setProdNo(String prodNo) {
		ProdNo = prodNo;
	}
	public String getSellerNo() {
		return SellerNo;
	}
	public void setSellerNo(String sellerNo) {
		SellerNo = sellerNo;
	}
	public String getCustNo() {
		return CustNo;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public String getProdName() {
		return ProdName;
	}
	public void setProdName(String prodName) {
		ProdName = prodName;
	}
	public String getProdIntro() {
		return ProdIntro;
	}
	public void setProdIntro(String prodIntro) {
		ProdIntro = prodIntro;
	}
	public Integer getPrice() {
		return Price;
	}
	public void setPrice(Integer price) {
		Price = price;
	}
	public Timestamp getIncreaseTime() {
		return IncreaseTime;
	}
	public void setIncreaseTime(Timestamp increaseTime) {
		IncreaseTime = increaseTime;
	}
	public String getProdStatus() {
		return ProdStatus;
	}
	public void setProdStatus(String prodStatus) {
		ProdStatus = prodStatus;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	public String getReceiveStatus() {
		return ReceiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		ReceiveStatus = receiveStatus;
	}
	public Timestamp getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		OrderTime = orderTime;
	}
	public String getTranMethod() {
		return TranMethod;
	}
	public void setTranMethod(String tranMethod) {
		TranMethod = tranMethod;
	}
	public String getTranAddr() {
		return TranAddr;
	}
	public void setTranAddr(String tranAddr) {
		TranAddr = tranAddr;
	}
	public String getProdType() {
		return ProdType;
	}
	public void setProdType(String prodType) {
		ProdType = prodType;
	}
	public Integer getEvaStar() {
		return EvaStar;
	}
	public void setEvaStar(Integer evaStar) {
		EvaStar = evaStar;
	}
	public byte[] getPhoto() {
		return Photo;
	}
	public void setPhoto(byte[] photo) {
		Photo = photo;
	}
	
	
}
