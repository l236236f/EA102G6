package com.sprod_detail.model;

import java.sql.Date;
import java.util.*;

public class Sprod_detailVO implements java.io.Serializable{
	private String orderNo;
	private String prodNo;
	private Integer quantity;
	private Date evaTime;
	private Integer evaStar;
	private String evaCont;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getEvaTime() {
		return evaTime;
	}
	public void setEvaTime(Date evaTime) {
		this.evaTime = evaTime;
	}
	public Integer getEvaStar() {
		return evaStar;
	}
	public void setEvaStar(Integer evaStar) {
		this.evaStar = evaStar;
	}
	public String getEvaCont() {
		return evaCont;
	}
	public void setEvaCont(String evaCont) {
		this.evaCont = evaCont;
	}
	
}
