package com.network.social.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.network.social.user.models.FriendRequest;
import com.network.social.user.models.User;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

	Optional<FriendRequest> findByFromAndTo(User user, User user2);

}
