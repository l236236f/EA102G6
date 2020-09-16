package com.game.model;

import java.util.Map;

public class GameMessage {
	private String sender;
	private String roomNo;
	private String type;
	private String message;
	private String penColor;
	private String penSize;
	private Map<String,String>[] drowMap;
	private Map<String,Integer> pointMap;
	
	public Map<String, String>[] getMap() {
		return drowMap;
	}

	public Map<String, String>[] getDrowMap() {
		return drowMap;
	}

	public void setDrowMap(Map<String, String>[] drowMap) {
		this.drowMap = drowMap;
	}

	public Map<String, Integer> getPointMap() {
		return pointMap;
	}

	public void setPointMap(Map<String, Integer> pointMap) {
		this.pointMap = pointMap;
	}

	public void setMap(Map<String, String>[] map) {
		this.drowMap = map;
	}

	public String getPenColor() {
		return penColor;
	}

	public void setPenColor(String penColor) {
		this.penColor = penColor;
	}

	public String getPenSize() {
		return penSize;
	}

	public void setPenSize(String penSize) {
		this.penSize = penSize;
	}

	public GameMessage() {
		
	}
	
	public GameMessage(String type,String message) {
		this.type = type;
		this.message = message;
	}
	
	public GameMessage(String type,String message,String roomNo,String sender) {
		this.type = type;
		this.message = message;
		this.roomNo = roomNo;
		this.sender = sender;
	}
	
	
	
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
