package com.rep_response.model;

import java.sql.Timestamp;

public class RepResponseVO {
	
	private String repno;       //���|�s��
	private String memno;       //�|���s��
	private String resno;       //�^�нs��
	private Timestamp reptime;  //���|�ɶ�
	private String repreason;   //���|��]
	private String repstatus;   //�B�z���|���A
	
	public String getRepno() {
		return repno;
	}
	public void setRepno(String repno) {
		this.repno = repno;
	}
	
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	
	public Timestamp getReptime() {
		return reptime;
	}
	public void setReptime(Timestamp reptime) {
		this.reptime = reptime;
	}
	
	public String getRepreason() {
		return repreason;
	}
	public void setRepreason(String repreason) {
		this.repreason = repreason;
	}
	
	public String getRepstatus() {
		return repstatus;
	}
	public void setRepstatus(String repstatus) {
		this.repstatus = repstatus;
	}	

}
