package com.rep_article.model;

import java.sql.Timestamp;

public class RepArticleVO {
	
	private String repno;       //���|�s��
	private String memno;       //�|���s��
	private String artno;       //�峹�s��
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
	
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
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
