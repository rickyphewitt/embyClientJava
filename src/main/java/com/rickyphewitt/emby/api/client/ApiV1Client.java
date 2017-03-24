package com.rickyphewitt.emby.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rickyphewitt.emby.api.constants.EmbyUrlConstants;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.AuthenticationRequest;
import com.rickyphewitt.emby.api.data.wrappers.AuthenticationResult;

@Service
public class ApiV1Client {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${emby.url}")
	private String embyUrl;

	@Value("${emby.username}")
	private String username;

	@Value("${emby.password}")
	private String password;
	
	private static String accessToken;
	
	// Core Api methods
	public AuthenticationResult authenticateByName() {
		accessToken = null;
		
		AuthenticationRequest authRequest = this.setCredentials();
		AuthenticationResult authResult = restTemplate.postForObject(embyUrl +"/"+ EmbyUrlConstants.AUTH_BY_NAME, authRequest, AuthenticationResult.class);
		
		// set access token from result for subsequent calls
		accessToken = authResult.getAccessToken();
		return authResult; 
	}
	
	public ArtistSet getArtists() {
		return restTemplate.getForObject(embyUrl + "/"+ EmbyUrlConstants.ARTISTS, ArtistSet.class);
	}
	
	// Getters
	public static String getAccessToken() {
		return accessToken;
	}
	
	// Helper methods
	private AuthenticationRequest setCredentials() {
		AuthenticationRequest authRequest = new AuthenticationRequest();
		authRequest.setUsername(username);
		authRequest.setPassword(password);
		
		return authRequest;
	}
	
}
