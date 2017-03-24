package com.rickyphewitt.emby.api.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.client.ApiV1Client;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ItemSet;

@Service
public class SampleService {

	@Autowired
	ApiV1Client apiClient;
	
	public SampleService() {}
	
	public void login() {
		apiClient.authenticateByName();
	}
	
	public ItemSet<Artist> getArtists() {
		return apiClient.getArtists();
	}
}
