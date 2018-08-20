package com.network.social.user.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.social.user.models.RegistrationDto;
import com.network.social.user.models.User;
import com.network.social.user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public void register(RegistrationDto dto) {
		User user = modelMapper.map(dto, User.class);
		userRepository.register(user);
	}

}
