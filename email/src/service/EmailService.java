package com.email.service;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String message, String subject, String to) {
		
		String from="kirti.techsoft2023@gmail.com";
		
		boolean f= false;
		
		String host = "smtp.gmail.com";
		//get the system properties
		  Properties properties=System.getProperties();
		  System.out.println("PROPERTIES"+ properties);
		  // setting important information to project object
		  //host set
		  properties.put("mail.smtp.host", host);
		  properties.put("mail.smtp.port", "465");
		  properties.put("mail.smtp.ssl.enable", "true");
		  properties.put("mail.smtp.auth", "true");
	         
		  // Step 1: to get the session object..
	Session session	 = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				//return super.getPasswordAuthentication() {
				return new PasswordAuthentication("kirti.techsoft2023@gmail.com", "rnwnqxsuyuwtgjsa");
						
			}
			  
			  
		  });
	session.setDebug(true);
	 // Step2 : Compose the message[test,multi media]  
	 MimeMessage m =  new MimeMessage(session);
	 
	 try {
	 //  from email id
	 m.setFrom(from);
	
	// adding recipient to message
	 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 
	 //adding subject to message
	 m.setSubject(subject);
	 
	 //adding text to message
	 m.setText(message);
	 // send 
	 //step 3: send the message using Transport class
	 Transport.send(m);
	 System.out.println("Send success.....!");
	 f=true;
	 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return f;
	
	}
}
	

