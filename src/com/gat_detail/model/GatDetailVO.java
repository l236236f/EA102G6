package com.gat_detail.model;

import java.sql.Date;

public class GatDetailVO {
	
	private String gatNo;
	private String memNo;
	private Date joinTime;
	
	public String getGatNo() {
		return gatNo;
	}
	public void setGatNo(String gatNo) {
		this.gatNo = gatNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	
	
}
