package com.network.social.user.exceptions.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.network.social.user.dtos.ResponseDto;
import com.network.social.user.exceptions.EmailAlreadyRegisteredException;
import com.network.social.user.exceptions.FriendRequestAlreadySentException;
import com.network.social.user.exceptions.FriendRequestDoesNotExist;
import com.network.social.user.exceptions.InvalidLinkException;
import com.network.social.user.exceptions.UnathorizedException;
import com.network.social.user.exceptions.UserNotActivatedException;
import com.network.social.user.exceptions.UserNotFoundException;

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
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseDto> handleUserNotFoundException(UserNotFoundException e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("user_not_found"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotActivatedException.class)
	public ResponseEntity<ResponseDto> handleUserNotActivatedException(UserNotActivatedException e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("user_not_activated"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FriendRequestAlreadySentException.class)
	public ResponseEntity<ResponseDto> handleFriendRequestAlreadySentException(FriendRequestAlreadySentException e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("frnd_req_already_sent"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FriendRequestDoesNotExist.class)
	public ResponseEntity<ResponseDto> handleFriendRequestDoesNotExist(FriendRequestDoesNotExist e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("req_not_exist"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnathorizedException.class)
	public ResponseEntity<ResponseDto> handleUnathorizedException(UnathorizedException e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("unauth"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> handleEmailAlreadyUsed(Exception e) {
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("exception"));
		
		logger.info(dto.getMessage(), e);
		
		return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
