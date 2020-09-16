package com.gat_weather.model;

public class WeatherVO {
	
	private String county;
	private String starttime;
	private String endtime;
	private String totalDescription;
	private String typeCode;
	private String type;
	private String temperature;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTotalDescription() {
		return totalDescription;
	}
	public void setTotalDescription(String totalDescription) {
		this.totalDescription = totalDescription;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
}
