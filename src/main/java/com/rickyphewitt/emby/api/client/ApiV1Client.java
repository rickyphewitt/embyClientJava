package com.rickyphewitt.emby.api.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rickyphewitt.emby.api.constants.EmbyUrlConstants;
import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.AuthenticationRequest;
import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.api.http.query.params.AlbumSetQueryParams;
import com.rickyphewitt.emby.api.http.query.params.QueryParams;
import com.rickyphewitt.emby.api.http.query.params.SongQueryParams;
import com.rickyphewitt.emby.api.http.query.params.SongSetQueryParams;

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
	
	/**
	 * Returns an ArtistSet object with a list of Artist items
	 * <p>
	 * @return      ArtistSet
	 * @see         Artist
	 */
	public ArtistSet getArtists() {
		return restTemplate.getForObject(embyUrl + "/"+ EmbyUrlConstants.ARTISTS, ArtistSet.class);
	}
	
	/**
	 * Returns an AlbumSet object with a list of Album items
	 * This method uses default Album Query params
	 * <p>
	 * @param	artistId the id of the artist whose albums are to be returned
	 * @return      AlbumSet
	 * @see         Album
	 * @see			AlbumSetQueryParams
	 */
	public AlbumSet getAlbumsByArtist(String artistId) {
		AlbumSetQueryParams queryParams = new AlbumSetQueryParams(artistId);
		URI targetUrl= buildUriWithQueryParams("/"+ EmbyUrlConstants.ITEMS, queryParams);
		return restTemplate.getForObject(targetUrl, AlbumSet.class);
	}
	
	public SongSet getAlbumSongs(String albumId) {
		//http://emby:8096/Items?ParentId=2e9d34a5c37842d768ee9c6c2ebe4a15&SortBy=SortName
		SongSetQueryParams queryParams = new SongSetQueryParams(albumId);
		URI targetUrl= buildUriWithQueryParams("/"+ EmbyUrlConstants.ITEMS, queryParams);
		return restTemplate.getForObject(targetUrl, SongSet.class);
		
		
	}
	
	public byte[] getSong(String songId) {
		//http://emby:8096/Audio/d45874f39662b31167c40062986acddc/stream.mp3?static=true
		SongQueryParams queryParams = new SongQueryParams();
		URI targetUri= buildUriWithQueryParams("/"+ EmbyUrlConstants.AUDIO + "/" + songId + "/" + EmbyUrlConstants.STREAM_MP3, queryParams);
		return restTemplate.getForObject(targetUri, byte[].class);
		
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
	
	private URI buildUriWithQueryParams(String path, QueryParams queryParams) {
		URI targetUrl= UriComponentsBuilder.fromUriString(embyUrl)
			    .path(path)
			    .replaceQueryParams(queryParams.getQueryParams())
			    .build()
			    .toUri();
		
		return targetUrl;
	}
	
}
