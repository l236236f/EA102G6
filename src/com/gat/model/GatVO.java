package com.gat.model;
import java.sql.*;

public class GatVO {

	private String gatNo;
	private String memNo;
	private String gatName;
	private Date gatTime;
	private String gatLoc;
	private String gatIntro;
	private Date gatStarttime;
	private Date gatEndtime;
	private Integer gatMax;
	private Integer gatMin;
	private Integer gatAll;
	private String gatStatus;
	private byte[] gatPhoto;
	private String gatType;
	private Double gatLat;
	private Double gatLng;
	
	public Double getGatLat() {
		return gatLat;
	}
	public void setGatLat(Double gatLat) {
		this.gatLat = gatLat;
	}
	public Double getGatLng() {
		return gatLng;
	}
	public void setGatLng(Double gatLng) {
		this.gatLng = gatLng;
	}
	public String getGatType() {
		return gatType;
	}
	public void setGatType(String gatType) {
		this.gatType = gatType;
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
	public String getGatName() {
		return gatName;
	}
	public void setGatName(String gatName) {
		this.gatName = gatName;
	}
	public Date getGatTime() {
		return gatTime;
	}
	public void setGatTime(Date gatTime) {
		this.gatTime = gatTime;
	}
	public String getGatLoc() {
		return gatLoc;
	}
	public void setGatLoc(String gatLoc) {
		this.gatLoc = gatLoc;
	}
	public String getGatIntro() {
		return gatIntro;
	}
	public void setGatIntro(String gatIntro) {
		this.gatIntro = gatIntro;
	}
	public Date getGatStarttime() {
		return gatStarttime;
	}
	public void setGatStarttime(Date gatStarttime) {
		this.gatStarttime = gatStarttime;
	}
	public Date getGatEndtime() {
		return gatEndtime;
	}
	public void setGatEndtime(Date gatEndtime) {
		this.gatEndtime = gatEndtime;
	}
	public Integer getGatMax() {
		return gatMax;
	}
	public void setGatMax(Integer gatMax) {
		this.gatMax = gatMax;
	}
	public Integer getGatMin() {
		return gatMin;
	}
	public void setGatMin(Integer gatMin) {
		this.gatMin = gatMin;
	}
	public Integer getGatAll() {
		return gatAll;
	}
	public void setGatAll(Integer gatAll) {
		this.gatAll = gatAll;
	}
	public String getGatStatus() {
		return gatStatus;
	}
	public void setGatStatus(String gatStatus) {
		this.gatStatus = gatStatus;
	}
	public byte[] getGatPhoto() {
		return gatPhoto;
	}
	public void setGatPhoto(byte[] gatPhoto) {
		this.gatPhoto = gatPhoto;
	}
	
	
}
