package com.network.social.user.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.models.RegistrationDto;
import com.network.social.user.models.User;
import com.network.social.user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void register(RegistrationDto dto) throws EmailAlreadyRegisteredException {
		User user = modelMapper.map(dto, User.class);
		
		Optional<User> optionalOldUser = userRepository.findByEmail(dto.getEmail());
		
		if(optionalOldUser.isPresent()) {
			throw new EmailAlreadyRegisteredException("Email id already registered");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
	}

}
