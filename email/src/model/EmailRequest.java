package com.email.model;

public class EmailRequest {
  private String to;
  private String subject;
  private String message;

  public EmailRequest(String from, String subject, String message) {
	super();
	this.to = to;
	this.subject = subject;
	this.message = message;
}
public EmailRequest() {
	  
  }
 
public String getTo() {
	return to;
}

public void setTo(String to) {
	this.to = to;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {

	this.message = message;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {

	this.subject = subject;
}



@Override
public String toString() {
	// TODO Auto-generated method stub
	return "EmailRequest{"+"to='"+to+'\''+", subject='"+subject+'\''+", message='"+message+'\''+'}';
}

}
