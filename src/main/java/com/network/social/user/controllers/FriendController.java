package com.network.social.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.social.user.dtos.FriendRequestDto;
import com.network.social.user.dtos.ResponseDataDto;
import com.network.social.user.dtos.ResponseDto;
import com.network.social.user.exceptions.FriendRequestAlreadySentException;
import com.network.social.user.exceptions.FriendRequestDoesNotExist;
import com.network.social.user.exceptions.UnathorizedException;
import com.network.social.user.exceptions.UserNotFoundException;
import com.network.social.user.services.FriendService;

@RestController
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private Environment env;

	@PostMapping("/send-request/{id}")
	public ResponseEntity<ResponseDataDto<FriendRequestDto>> sendFriendRequest(@PathVariable int id, @RequestAttribute long userId) throws UserNotFoundException, FriendRequestAlreadySentException {
		FriendRequestDto dto = friendService.addFriendRequest(userId, id);
		
		ResponseDataDto<FriendRequestDto> dataDto = ResponseDataDto.fromPropeties(env.getProperty("successful"), dto);
		
		return new ResponseEntity<>(dataDto, HttpStatus.OK);
	}
	
	@GetMapping("/requests")
	public ResponseEntity<ResponseDataDto<List<FriendRequestDto>>> getFriendRequests(@RequestAttribute long userId) {
		List<FriendRequestDto> requests = friendService.getFriendRequests(userId);
		
		ResponseDataDto<List<FriendRequestDto>> dataDto = ResponseDataDto.fromPropeties(env.getProperty("successful"), requests);
		
		return new ResponseEntity<>(dataDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/requests/{id}")
	public ResponseEntity<ResponseDto> deleteRequest(@PathVariable long id, @RequestAttribute long userId) throws FriendRequestDoesNotExist, UnathorizedException {
		friendService.deleteFriendRequest(id, userId);
		
		ResponseDto dto = ResponseDto.fromPropeties(env.getProperty("successful"));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("requests/sent")
	public ResponseEntity<ResponseDataDto<List<FriendRequestDto>>> getSentFriendRequests(@RequestAttribute long userId) {
		List<FriendRequestDto> requests = friendService.getSentFriendRequests(userId);
		
		ResponseDataDto<List<FriendRequestDto>> dataDto = ResponseDataDto.fromPropeties(env.getProperty("successful"), requests);
		
		return new ResponseEntity<>(dataDto, HttpStatus.OK);
	}
	
	@PutMapping("requests/accept/{id}")
	public void acceptFriendRequest(@PathVariable long id, @RequestAttribute long userId) {
		friendService.acceptFriendRequest(id, userId);
	}
	
}
