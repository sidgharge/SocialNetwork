package com.network.social.user.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.network.social.user.exceptions.UserNotActivatedException;
import com.network.social.user.models.User;
import com.network.social.user.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if(!optionalUser.isPresent()) {
			throw new UsernameNotFoundException("Invalid credentials");
		}
		
		User user = optionalUser.get();
		
		if(!user.isActivated()) {
			throw new UserNotActivatedException();
		}
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		
		return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPassword(), authorities);
	}

}
