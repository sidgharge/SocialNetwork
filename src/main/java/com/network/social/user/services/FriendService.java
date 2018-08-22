package com.network.social.user.services;

import java.util.List;

import com.network.social.user.dtos.FriendRequestDto;
import com.network.social.user.exceptions.FriendRequestAlreadySentException;
import com.network.social.user.exceptions.FriendRequestDoesNotExist;
import com.network.social.user.exceptions.UnathorizedException;
import com.network.social.user.exceptions.UserNotFoundException;
import com.network.social.user.models.FriendRequest;

public interface FriendService {

	FriendRequestDto addFriendRequest(long senderId, long receiverId) throws UserNotFoundException, FriendRequestAlreadySentException;

	List<FriendRequestDto> getFriendRequests(long userId);

	List<FriendRequestDto> getSentFriendRequests(long userId);

	void deleteFriendRequest(long id, long userId) throws FriendRequestDoesNotExist, UnathorizedException;

	void acceptFriendRequest(long id, long userId);

}
