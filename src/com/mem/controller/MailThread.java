package com.mem.controller;

public class MailThread extends Thread{
	
	private String memEmail;
	private String subject;
	private String memName;
	private String button;
	
	public MailThread(String memEmail, String subject, String memName,String button){
		this.memEmail = memEmail;
		this.subject = subject;
		this.memName = memName;
		this.button = button;
	}
	
	@Override
	public void run() {
		MailService mailService = new MailService(); //寄信件通知至action=fistTime進行驗證
	    mailService.sendMail(memEmail, subject, memName, button);
	}
}
