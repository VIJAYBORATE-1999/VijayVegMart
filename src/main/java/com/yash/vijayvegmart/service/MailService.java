package com.yash.vijayvegmart.service;

import jakarta.mail.MessagingException;

public interface MailService {
	
	public void sendEmail(String destionationemail, String messageBody) throws MessagingException;
}
