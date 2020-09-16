package com.article.model;

import java.sql.Timestamp;

public class ArticleVO {
	
	private String artno;       	//�峹�s��
	private String memno;      		//�|���s��
	private String arttitle;    	//�峹���D
	private String artcontent;  	//�峹���e
	private Timestamp arttime;      //�o�G�ɶ�
	private Integer gpcount;    	//�g��
	private Integer favcount;   	//�l�ܼ�
	private String artstatus;   	//�峹���A
	private Integer rescount;   	//�^�м�
	
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
	
	public String getArttitle() {
		return arttitle;
	}
	public void setArttitle(String arttitle) {
		this.arttitle = arttitle;
	}
	
	public String getArtcontent() {
		return artcontent;
	}
	public void setArtcontent(String artcontent) {
		this.artcontent = artcontent;
	}
	
	public Timestamp getArttime() {
		return arttime;
	}
	public void setArttime(Timestamp arttime) {
		this.arttime = arttime;
	}
	
	public Integer getGpcount() {
		return gpcount;
	}
	public void setGpcount(Integer gpcount) {
		this.gpcount = gpcount;
	}
	
	public Integer getFavcount() {
		return favcount;
	}
	public void setFavcount(Integer favcount) {
		this.favcount = favcount;
	}
	
	public String getArtstatus() {
		return artstatus;
	}
	public void setArtstatus(String artstatus) {
		this.artstatus = artstatus;
	}
	
	public Integer getRescount() {
		return rescount;
	}
	public void setRescount(Integer rescount) {
		this.rescount = rescount;
	}
	
}
