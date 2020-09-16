package CHATIMG.model;

public class CHATIMGVO implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private String chatimgno;
	private byte[] chatimg;
	public byte[] getChatimg() {
		return chatimg;
	}
	public void setChatimg(byte[] chatimg) {
		this.chatimg = chatimg;
	}
	public String getChatimgno() {
		return chatimgno;
	}
	public void setChatimgno(String chatimgno) {
		this.chatimgno = chatimgno;
	}
	
	
	
	
	
}
