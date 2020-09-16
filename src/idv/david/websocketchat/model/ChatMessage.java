package idv.david.websocketchat.model;


public class ChatMessage {
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String messagetime;
	private String mesgStatus;
	
	public ChatMessage(String type, String sender, String receiver, String message,String messagetime,String mesgstatus) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.messagetime=messagetime;
		this.mesgStatus=mesgstatus;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessagetime() {
		return messagetime;
	}

	public void setMessagetime(String messagetime) {
		this.messagetime = messagetime;
	}

	public String getMesgStatus() {
		return mesgStatus;
	}

	public void setMesgStatus(String mesgStatus) {
		this.mesgStatus = mesgStatus;
	}
}
