package com.rickyphewitt.emby.api.data.wrappers;

import com.rickyphewitt.emby.api.data.User;

public class AuthenticationResult {
	
	private User user;
	private String accessToken;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
