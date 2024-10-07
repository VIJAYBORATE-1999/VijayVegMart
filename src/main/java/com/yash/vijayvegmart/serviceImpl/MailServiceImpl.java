package com.yash.vijayvegmart.serviceImpl;

import com.yash.vijayvegmart.service.MailService;
import com.yash.vijayvegmart.util.MailUtil;

import jakarta.mail.MessagingException;

public class MailServiceImpl  implements MailService{

	
	/* -------CLOSE  DB   CONNECTION -----------   */
	
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendEmail(String destionationemail, String messageBody) throws MessagingException {
		// TODO Auto-generated method stub
		
	}
}
