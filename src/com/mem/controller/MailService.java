package com.mem.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailService {
	
	// �]�w�ǰe�l��:�ܦ��H�H��Email�H�c,Email�D��,Email���e
	public void sendMail(String to, String subject, String memName, String button) {
			
	   try {
		   // �]�w�ϥ�SSL�s�u�� Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ���]�w gmail ���b�� & �K�X (�N�ǥѧA��Gmail�ӶǰeEmail)
       // �����NmyGmail���i�w���ʸ��C�����ε{���s���v�j���}
	     final String myGmail = "EA102G6@gmail.com";
	     final String myGmail_password = "EA102GGYY";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setContent(
							"<div style='background-color: #edf0f3;'><div style='margin: 0 auto; background-image:url(https://media.gettyimages.com/photos/geometric-shapes-in-black-and-white-abstract-background-picture-id1043644872?b=1&k=6&m=1043644872&s=612x612&w=0&h=YOpxGhXWagZRkAqgTpIY2U_UxYC6cloFk0Ev9CJrG1w=);"
							+ "background-repeat:no-repeat;" 
							+ "background-size:cover; width:500px; height:200px'>"
							+ "<div style='padding: 20px;'><H1 style='text-align:center; color: white;'>Hello!" + memName + "</H1>"
							+ "<p style='color: white; text-align: center;'>�I�����s�i��|������</p>"
							+ "<div style='margin-top:10px;'><a href=" + button + " style='text-decoration:none;'><div style='border: 1px solid white; width:fit-content; margin:0 auto; background-color:white; border-radius:2px; line-height:40px; font-size:15px; color:#202124;'><div style='margin: 0 12px;'>���ҥh</div></div></a></div></div></div>"
//							+ "<div style='text-align:center; color: white;'>�K�K!!!</div>"
							+ "</div>"
							+"<div align='center' style='color: black;'>���l��Ѩt�Φ۰ʱH�o�A�ФŪ����^�СC</div>"
							+"<div align='center' style='color: black;'>@2020 GGYY CKY, Tristan Chen, ZZ Tibame, EA102G6, Taiwan</div>",
							"text/html; charset=UTF-8");
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// �]�w�H�����D��
			message.setSubject(subject);

		   Transport.send(message);
		   System.out.println("�ǰe���\!");
     }catch (MessagingException e){
	     System.out.println("�ǰe����!");
	     e.printStackTrace();
     }
   }

}