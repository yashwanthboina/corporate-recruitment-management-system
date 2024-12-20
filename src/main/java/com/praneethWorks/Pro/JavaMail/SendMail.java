package com.praneethWorks.Pro.JavaMail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.*;

public class SendMail {
	
	private Session newSession;
	
	private MimeMessage mimeMessage;
	
	private List<String> emailReceipients;
	
	private String title,companyName;
	
	public SendMail() {
		setupServerProperties();
		emailReceipients=new ArrayList<>();
		
	}
	
	public void send(String tile,String companyName,List<String> emailReceipients) throws AddressException, MessagingException, IOException {
		this.title=tile;
		this.companyName=companyName;
		this.emailReceipients=emailReceipients;
		draftEmail();
		sendEmail();
		
	}
	
	private void setupServerProperties() {
		Properties properties= System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties, null);
	}
	
	private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {
		
		String emailSubject = "We found job for you :)";
		String emailBody ="<P style=\"color:black;font-weight:bold;\">"+title+"</p>"
				+ "<p>by "+companyName+"</p>";
		mimeMessage = new MimeMessage (newSession);
		for (int i=0; i<emailReceipients.size();i++)
		{
			mimeMessage.addRecipient (Message.RecipientType.TO, new InternetAddress (emailReceipients.get(i)));
		}
		mimeMessage.setSubject(emailSubject);
		
		
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(emailBody, "text/html");
		MimeMultipart multiPart = new MimeMultipart(); 
		multiPart.addBodyPart(bodyPart);
		mimeMessage.setContent(multiPart);
		return mimeMessage;
	}
	
	private void sendEmail() throws MessagingException {
		String fromUser = "praneethscienceprojectscodes@gmail.com"; //Enter sender email id
		String fromUserPassword = "rmfb lqlq pwbx bhhv"; //Enter sender gmail password, this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport =newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent!!!");
	}

}
