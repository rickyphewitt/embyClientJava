# Spring Boot Java Emby API Client

An alternative API client for Emby Media Server based on: https://github.com/MediaBrowser/Emby.ApiClient.Java

## NOTE: This API Client is in ALPHA (PRE) 
* Expect breaking changes!
* Jar is not hosted anywhere, will need to build from source and add the jar locally

## Main Differences from Official Client

 * Currently only supports music
 * No Andriod dependencies
 * Leverages Spring Boot
 * All API calls are synchronous

## Build
* mvn clean install
* Then include in a java project of your choice

## Currently supported api calls 
* getPublicServerInfo
* getPublicUsers
* authenticateByName
* getArtists
* getAlbumsByArtist
* getAlbumSongs
* getSong

## Creating a sample service
* There is a sample service and test included that can be run after adding auth/server info to the properties file.
An example is below with locations of noteworthy files
    * SampleService: com/rickyphewitt/emby/api/services/sample/SampleService.java
    * Test:test/com/rickyphewitt/emby/api/services/sample/ServiceSampleTests.java
    * Properties: services/src/main/resources/application.properties
``` java
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


```

## More examples
* Used in [seamless](https://github.com/rickyphewitt/seamless) music player

## Want to contribute?
* There are plenty of unimplemented Emby Api's :)
* The primary focus of this client at the moment on music, however other media integrations are encouraged!