package com.egasbooking.app.dto;

public class LoginResultDto {

	public String loginMessage;
	public String email;
	public int id;

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userName) {
		this.email = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoginResultDto [loginMessage=" + loginMessage + ", email=" + email + ", id=" + id + "]";
	}

}
