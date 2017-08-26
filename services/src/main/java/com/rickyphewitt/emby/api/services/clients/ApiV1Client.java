package com.rickyphewitt.emby.api.services.clients;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.AuthenticationRequest;
import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.api.services.constants.EmbyUrlConstants;
import com.rickyphewitt.emby.api.services.http.query.params.AlbumSetQueryParams;
import com.rickyphewitt.emby.api.services.http.query.params.QueryParams;
import com.rickyphewitt.emby.api.services.http.query.params.SongQueryParams;
import com.rickyphewitt.emby.api.services.http.query.params.SongSetQueryParams;

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

	@Value("${emby.password.default}")
	private String passwordDefault;
	
	private static String accessToken;
	
	public ApiV1Client(){}
	
	public ApiV1Client(String embyUrl, String username, String password) {
		this.embyUrl = embyUrl;
		this.username = username;
		this.password = password;
	}
	
	
	// Core Api methods
	/**
	 * Returns the public server information for a given emby server
	 * <p>
	 * @param	String url of the emby server
	 * @return      PublicServerInfo
	 */
	public PublicServerInfo getPublicServerInfo (String embyUrl) {
		this.embyUrl = embyUrl;
		return restTemplate.getForObject(embyUrl + "/" + EmbyUrlConstants.PUBLIC_SERVER_INFO, PublicServerInfo.class);
	}
	
	/**
	 * Returns the list of public users for a given emby server
	 * <p>
	 * @param	String url of the emby server
	 * @return      PublicServerInfo
	 */
	public UserSet getPublicUsers() {
		//users/public
		return restTemplate.getForObject(this.embyUrl + "/" + EmbyUrlConstants.PUBLIC_USERS, UserSet.class);
		
	}
	
	/**
	 * Authenticates a user by their username and pass
	 * <p>
	 * @param	String username username of the user
	 * @param	String password password of the user
	 * @return      AuthenticationResult
	 * @see         authenticateByName
	 */
	public AuthenticationResult authenticateByName(String username, String password) {
		accessToken = null;
		AuthenticationRequest authRequest = this.setCredentials();
		AuthenticationResult authResult = restTemplate.postForObject(embyUrl +"/"+ EmbyUrlConstants.AUTH_BY_NAME, authRequest, AuthenticationResult.class);
		
		// set access token from result for subsequent calls
		accessToken = authResult.getAccessToken();
		return authResult; 
	}
	
	/**
	 * Authenticates a user by their username using spring {@value}'s
	 * as set in the application.properties file
	 * <p>
	 * @return      AuthenticationResult
	 * @see         authenticateByName
	 */
	public AuthenticationResult authenticateByName() {
		return authenticateByName(username, password);
	}
	
	/**
	 * Authenticates a user by their username and the default password
	 * This should be used if a user's hasPassword = False
	 * <p>
	 * @param		String username name of the user
	 * @return      AuthenticationResult
	 * @see         authenticateByName
	 */
	public AuthenticationResult authenticateByName(String username) {
		this.username = username;
		password = passwordDefault;
		return authenticateByName(username, password);
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

	// Getters/Setters
	
	public String getEmbyUrl() {
		return embyUrl;
	}

	public void setEmbyUrl(String embyUrl) {
		this.embyUrl = embyUrl;
	}

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
	}
	
}
