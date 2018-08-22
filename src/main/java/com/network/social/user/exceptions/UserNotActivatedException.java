package com.network.social.user.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotActivatedException extends UsernameNotFoundException {

	private static final long serialVersionUID = 1L;

	public UserNotActivatedException() {
		super("User is not activated");
	}

}
