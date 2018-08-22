package com.network.social.user.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.social.user.dtos.FriendRequestDto;
import com.network.social.user.exceptions.FriendRequestAlreadySentException;
import com.network.social.user.exceptions.FriendRequestDoesNotExist;
import com.network.social.user.exceptions.UnathorizedException;
import com.network.social.user.exceptions.UserNotFoundException;
import com.network.social.user.models.FriendRequest;
import com.network.social.user.models.User;
import com.network.social.user.repositories.FriendRequestRepository;
import com.network.social.user.repositories.UserRepository;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendRequestRepository friendRequestRepository;

	@Override
	public FriendRequestDto addFriendRequest(long senderId, long receiverId)
			throws UserNotFoundException, FriendRequestAlreadySentException {
		User receiver = checkIfUserExists(receiverId);

		User sender = getLoggedInUser(senderId);

		Optional<FriendRequest> optionalOldFriendRequest = friendRequestRepository.findByFromAndTo(sender, receiver);
		if (optionalOldFriendRequest.isPresent()) {
			throw new FriendRequestAlreadySentException();
		}

		FriendRequest friendRequest = FriendRequest.fromFromAndTo(sender, receiver);

		friendRequestRepository.save(friendRequest);

		FriendRequestDto dto = modelMapper.map(friendRequest, FriendRequestDto.class);

		return dto;
	}
	
	@Override
	public List<FriendRequestDto> getFriendRequests(long userId) {
		User user = getDummyLoggedInUser(userId);
		List<FriendRequest> requests = friendRequestRepository.findByTo(user);
		return requests.stream().map(req -> modelMapper.map(req, FriendRequestDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public List<FriendRequestDto> getSentFriendRequests(long userId) {
		User user = getDummyLoggedInUser(userId);
		List<FriendRequest> requests = friendRequestRepository.findByFrom(user);
		return requests.stream().map(req -> modelMapper.map(req, FriendRequestDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public void deleteFriendRequest(long id, long userId) throws FriendRequestDoesNotExist, UnathorizedException {
		Optional<FriendRequest> optionalRequest = friendRequestRepository.findById(id);
		
		if(!optionalRequest.isPresent()) {
			throw new FriendRequestDoesNotExist();
		}
		
		FriendRequest request = optionalRequest.get();
		
		if(request.getFrom().getId() != userId && request.getTo().getId() != userId) {
			throw new UnathorizedException();
		}
		
		friendRequestRepository.delete(request);
	}
	
	@Override
	public void acceptFriendRequest(long id, long userId) {
		
	}

	private User checkIfUserExists(long userId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);

		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException();
		}
		return optionalUser.get();
	}

	private User getLoggedInUser(long userId) {
		return userRepository.findById(userId).get();
	}
	
	private User getDummyLoggedInUser(long userId) {
		User user = new User();
		user.setId(userId);
		return user;
	}

}
