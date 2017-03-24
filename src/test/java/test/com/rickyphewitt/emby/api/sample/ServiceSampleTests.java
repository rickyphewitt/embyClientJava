package test.com.rickyphewitt.emby.api.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ItemSet;
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
		sampleService.login();
	
		ItemSet<Artist> artists = sampleService.getArtists();
		
		Assert.assertTrue(artists.getItems().size() > 1);
		
		
	}

}
