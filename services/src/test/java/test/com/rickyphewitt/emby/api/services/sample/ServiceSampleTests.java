package test.com.rickyphewitt.emby.api.services.sample;

import com.rickyphewitt.emby.api.data.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.services.sample.SampleService;

import junit.framework.Assert;
import test.com.rickyphewitt.emby.api.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ServiceSampleTests {

	@Autowired
	SampleService sampleService;
	
	@Value("${emby.url}")
	private String embyUrl;

	// This test class simulates a service
	// retrieving artists/albums/songs and
	// the byte stream of a single song after
	// adding user/pass/server info to the properties
	// file located at services/src/main/resources/application.properties
	// comment the below @Ignore line out to run the test.

	@Test
	@Ignore("Comment this line out to run the test!")
	public void login() {
		// Get public server info
		PublicServerInfo serverInfo = sampleService.getPublicServerInfo(embyUrl);
		Assert.assertTrue(serverInfo != null);

		UserSet users = sampleService.getPublicUsers();
		Assert.assertTrue(users.getItems().size() > 0);


		// Log into emby server
		sampleService.login();

		// get all artists
		ArtistSet artists = sampleService.getArtists();
		Assert.assertTrue(artists.getItems().size() > 0);

		// get albums by artist
		AlbumSet albums = sampleService.getAlbumsByArtist(artists.getItems().get(1).getId());
		Assert.assertTrue(albums.getItems().size() > 0);
		for(Album album: albums.getItems()) {
			Assert.assertNotNull(album.getAlbumArtists());
			for(Item albumArtist: album.getAlbumArtists()) {
				Assert.assertNotNull(albumArtist.getId());
				Assert.assertNotNull(albumArtist.getName());
			}
		}

		// get songs by album
		SongSet songs = sampleService.getSongsFromAlbum(albums.getItems().get(0).getId());
		Assert.assertTrue(songs.getItems().size() > 0);

		// get single song
		byte[] songFile = sampleService.getSong(songs.getItems().get(0).getId());
		Assert.assertNotNull(songFile);

		// get primareyImage url
		String primaryImage = sampleService.getPrimaryImage(albums.getItems().get(0).getId(), albums.getItems().get(0).getPrimaryImage());
		Assert.assertNotNull(primaryImage);

		// get primaryImage
		byte[] image = sampleService.getImage(primaryImage);
		Assert.assertNotNull(image);

		// get all albums
		AlbumSet allAlbums = sampleService.getAlbums();
		Assert.assertNotNull(allAlbums);


	}

}
