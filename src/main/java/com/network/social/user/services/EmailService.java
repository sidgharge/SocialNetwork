package com.network.social.user.services;

import javax.mail.MessagingException;

import com.network.social.user.models.Email;

public interface EmailService {
	
	void sendEmail(Email email) throws MessagingException;

}
