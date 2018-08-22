package com.network.social.user.dtos;

public class ResponseDataDto<T> extends ResponseDto {
	
	public ResponseDataDto() {

	}
	
	public ResponseDataDto(String message, int status, T data) {
		super(message, status);
		this.data = data;
	}
	
	public static <T> ResponseDataDto<T> fromPropeties(String message, T data) {
		int index = message.indexOf("|");
		
		return new ResponseDataDto<>(message.substring(0, index), Integer.parseInt(message.substring(index+1)), data);
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
