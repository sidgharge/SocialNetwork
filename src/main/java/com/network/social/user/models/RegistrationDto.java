package com.network.social.user.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationDto {

	@NotEmpty
	@Size(min = 3, message = "First name must have at least 3 letters")
	private String firstname;

	@NotEmpty
	@Size(min = 3, message = "Last name must have at least 3 letters")
	private String lastname;

	@NotEmpty(message = "Email required")
	@Email(message = "Invalid email")
	private String email;

	@NotEmpty
	@Size(min = 10, max = 10)
	private String contact;

	@NotEmpty
	@Size(min = 8)
	private String password;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
