package com.ne.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequest {

	@NotBlank
	private String names;

	@Email
	private String email;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	private String nationalId;

	@NotBlank
	private String password;
	
	

	public SignUpRequest() {
		super();
	}

	public SignUpRequest(@NotBlank String names, @Email String email, @NotBlank String phoneNumber,
			@NotBlank String nationalId, @NotBlank String password) {
		super();
		this.names = names;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nationalId = nationalId;
		this.password = password;
	}



	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	
}
