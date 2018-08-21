package com.network.social.user.controllers;


import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.exceptions.InvalidLinkException;
import com.network.social.user.models.RegistrationDto;
import com.network.social.user.models.ResponseDto;
import com.network.social.user.services.UserService;
import com.network.social.user.util.Utility;

@PropertySource("classpath:messages.properties")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment env;

	@PostMapping("/register")
	public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegistrationDto registrationDto, HttpServletRequest request) throws EmailAlreadyRegisteredException, IOException, MessagingException {
		userService.register(registrationDto, Utility.getUrl(request));
		
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("registration_successful"));
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping("/activate")
	public void activate(@RequestParam String token) throws InvalidLinkException {
		userService.activateUser(token);
	}
	
}
