package com.article.model;

import java.sql.Timestamp;

public class ArticleVO {
	
	private String artno;       	//文章編號
	private String memno;      		//會員編號
	private String arttitle;    	//文章標題
	private String artcontent;  	//文章內容
	private Timestamp arttime;      //發佈時間
	private Integer gpcount;    	//讚數
	private Integer favcount;   	//追蹤數
	private String artstatus;   	//文章狀態
	private Integer rescount;   	//回覆數
	
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
