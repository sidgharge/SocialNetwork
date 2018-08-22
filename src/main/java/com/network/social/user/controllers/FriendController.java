package com.network.social.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.social.user.dtos.FriendRequestDto;
import com.network.social.user.dtos.ResponseDataDto;
import com.network.social.user.exceptions.FriendRequestAlreadySentException;
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
	public void getFriendRequests() {
		
	}
	
}
