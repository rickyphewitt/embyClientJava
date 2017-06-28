package com.rickyphewitt.emby.api.data;

public class User extends Item {
	
	private boolean hasPassword;
	private boolean enableAutoLogin;
	
	// Getters/Setters  
	public boolean isHasPassword() {
		return hasPassword;
	}
	public void setHasPassword(boolean hasPassword) {
		this.hasPassword = hasPassword;
	}
	public boolean isEnableAutoLogin() {
		return enableAutoLogin;
	}
	public void setEnableAutoLogin(boolean enableAutoLogin) {
		this.enableAutoLogin = enableAutoLogin;
	}
}