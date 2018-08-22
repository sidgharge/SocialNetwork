package com.network.social.user.services;

import com.network.social.user.dtos.FriendRequestDto;
import com.network.social.user.exceptions.FriendRequestAlreadySentException;
import com.network.social.user.exceptions.UserNotFoundException;

public interface FriendService {

	FriendRequestDto addFriendRequest(long senderId, long receiverId) throws UserNotFoundException, FriendRequestAlreadySentException;

}
