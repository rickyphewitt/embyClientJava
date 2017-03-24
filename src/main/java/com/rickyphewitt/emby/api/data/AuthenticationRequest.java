package com.rickyphewitt.emby.api.data;

public class AuthenticationRequest {

	private String username;
	private String password;
	private String passwordMd5;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		//@ToDo actually hash the password :)
		this.passwordMd5 = "hashedPasswordHere";
	}
	public String getPasswordMd5() {
		return passwordMd5;
	}
	
}
