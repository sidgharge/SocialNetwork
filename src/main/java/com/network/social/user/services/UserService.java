package com.network.social.user.services;

import java.io.IOException;

import javax.mail.MessagingException;

import com.network.social.user.dtos.RegistrationDto;
import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.exceptions.InvalidLinkException;

public interface UserService {

	void register(RegistrationDto dto, String url) throws EmailAlreadyRegisteredException, IOException, MessagingException;

	void activateUser(String token) throws InvalidLinkException;

}
