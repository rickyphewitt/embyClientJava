package com.rickyphewitt.emby.api.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.api.services.clients.ApiV1Client;

@Service
public class SampleService {

	@Autowired
	ApiV1Client apiClient;
	
	public SampleService() {}
	
	public PublicServerInfo getPublicServerInfo(String url) {
		return apiClient.getPublicServerInfo(url);
	}
	
	public UserSet getPublicUsers() {
		return apiClient.getPublicUsers();
	}
	
	@Retryable(maxAttempts = 5)
	public void login() {
		apiClient.authenticateByName();
	}
	
	public ArtistSet getArtists() {
		return apiClient.getArtists();
	}
	
	public AlbumSet getAlbumsByArtist(String artistId) {
		return apiClient.getAlbumsByArtist(artistId);
	}
	
	public SongSet getSongsFromAlbum(String albumId) {
		return apiClient.getAlbumSongs(albumId);
		
	}
	
	public byte[] getSong(String songId) {
		return apiClient.getSong(songId);
		
	}
}
