package com.art_response.model;

import java.sql.Timestamp;

public class ArtResponseVO {
	
	private String resno;          //�^�нs��
	private String artno;          //�峹�s��
	private String memno;          //�|���s��
	private String rescontent;     //�^�Ф��e
	private Timestamp restime;     //�o���ɶ�
	private String resstatus;      //�^�Ъ��A
	
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
	}
	
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	
	public String getRescontent() {
		return rescontent;
	}
	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}
	
	public Timestamp getRestime() {
		return restime;
	}
	public void setRestime(Timestamp restime) {
		this.restime = restime;
	}
	
	public String getResstatus() {
		return resstatus;
	}
	public void setResstatus(String resstatus) {
		this.resstatus = resstatus;
	}

}
