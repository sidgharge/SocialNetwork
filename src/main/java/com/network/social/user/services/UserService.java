package com.network.social.user.services;

import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.models.RegistrationDto;

public interface UserService {

	void register(RegistrationDto dto) throws EmailAlreadyRegisteredException;
}
