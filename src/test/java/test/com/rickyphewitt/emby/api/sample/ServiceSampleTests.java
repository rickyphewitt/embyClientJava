package test.com.rickyphewitt.emby.api.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.sample.SampleService;

import junit.framework.Assert;
import test.com.rickyphewitt.emby.api.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ServiceSampleTests {

	@Autowired
	SampleService sampleService;
	
	@Test
	public void login() {
		// Log into emby server
		sampleService.login();
	
		// get all artists
		ArtistSet artists = sampleService.getArtists();
		Assert.assertTrue(artists.getItems().size() > 0);
		
		// get albums by artist
		AlbumSet albums = sampleService.getAlbumsByArtist(artists.getItems().get(0).getId());
		Assert.assertTrue(albums.getItems().size() > 0);
		
		
		
	}

}
