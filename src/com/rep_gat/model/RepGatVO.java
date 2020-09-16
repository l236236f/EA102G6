package com.rep_gat.model;

import java.sql.Timestamp;

public class RepGatVO {
	
	private String repNo;
	private String gatNo;
	private String memNo;
	private String repCont;
	private Timestamp repTime;
	private String repStatus;
	
	public String getRepNo(){
		return repNo;
	}
	
	public void setRepNo(String repNo) {
		this.repNo = repNo;
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

	public String getRepCont() {
		return repCont;
	}

	public void setRepCont(String repCont) {
		this.repCont = repCont;
	}

	public Timestamp getRepTime() {
		return repTime;
	}

	public void setRepTime(Timestamp repTime) {
		this.repTime = repTime;
	}

	public String getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}
	
	

}
