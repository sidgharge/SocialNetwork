package com.network.social.user.models;

public class ResponseDataDto<T> extends ResponseDto {
	
	public ResponseDataDto() {

	}
	
	public ResponseDataDto(String message, int status, T data) {
		super(message, status);
		this.data = data;
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
