package com.network.social.user.models;

public class ResponseDto {
	
	public ResponseDto() {

	}
	
	public ResponseDto(String message, int status) {
		this.message = message;
		this.status = status;
	}
	
	public static ResponseDto fromPropeties(String message) {
		int index = message.indexOf("|");
		return new ResponseDto(message.substring(0, index), Integer.parseInt(message.substring(index+1)));
	}

	private String message;

	private int status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
