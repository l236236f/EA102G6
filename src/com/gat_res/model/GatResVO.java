package com.gat_res.model;

import java.sql.Timestamp;

public class GatResVO {
	
	private String resNo;
	private String gatNo;
	private String memNo;
	private String resCont;
	private Timestamp resTime;
	private String resReply;
	private String resStatus;
	
	public String getResNo() {
		return resNo;
	}
	public void setResNo(String resNo) {
		this.resNo = resNo;
	}
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
	public String getResCont() {
		return resCont;
	}
	public void setResCont(String resCont) {
		this.resCont = resCont;
	}
	public Timestamp getResTime() {
		return resTime;
	}
	public void setResTime(Timestamp resTime) {
		this.resTime = resTime;
	}
	public String getResReply() {
		return resReply;
	}
	public void setResReply(String resReply) {
		this.resReply = resReply;
	}
	public String getResStatus() {
		return resStatus;
	}
	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	
	

}
