package com.rickyphewitt.emby.api.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.client.ApiV1Client;
import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.ArtistSet;

@Service
public class SampleService {

	@Autowired
	ApiV1Client apiClient;
	
	public SampleService() {}
	
	public void login() {
		apiClient.authenticateByName();
	}
	
	public ArtistSet getArtists() {
		return apiClient.getArtists();
	}
	
	public AlbumSet getAlbumsByArtist(String artistId) {
		return apiClient.getAlbumsByArtist(artistId);
	}
}
