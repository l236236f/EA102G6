package com.fav_article.model;

import java.sql.Timestamp;

public class FavArticleVO {
	
	private String memno;       //會員編號
	private String artno;       //文章編號
	private Timestamp favtime;  //收藏時間
	
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
	
	public Timestamp getFavtime() {
		return favtime;
	}
	public void setFavtime(Timestamp favtime) {
		this.favtime = favtime;
	}
	
	

}
