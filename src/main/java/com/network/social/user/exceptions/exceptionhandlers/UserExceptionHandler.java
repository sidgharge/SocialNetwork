package com.network.social.user.exceptions.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.exceptions.InvalidLinkException;
import com.network.social.user.models.ResponseDto;

@ControllerAdvice
public class UserExceptionHandler {
	
	@Autowired
	private Environment env;
	
	private final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ResponseEntity<ResponseDto> handleEmailAlreadyUsed(EmailAlreadyRegisteredException e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("email_used"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidLinkException.class)
	public ResponseEntity<ResponseDto> handleEmailAlreadyUsed(InvalidLinkException e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("invalid_link"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> handleEmailAlreadyUsed(Exception e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("exception"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
