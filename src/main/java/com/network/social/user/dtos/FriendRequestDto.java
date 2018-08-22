package com.network.social.user.dtos;

import java.time.LocalDateTime;

public class FriendRequestDto {

	private long id;

	private UserDto from;

	private UserDto to;

	private LocalDateTime sentAt;
	
	public FriendRequestDto() {
		
	}

	public FriendRequestDto(long id, UserDto from, UserDto to, LocalDateTime sentAt) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.sentAt = sentAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDto getFrom() {
		return from;
	}

	public void setFrom(UserDto from) {
		this.from = from;
	}

	public UserDto getTo() {
		return to;
	}

	public void setTo(UserDto to) {
		this.to = to;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

}
