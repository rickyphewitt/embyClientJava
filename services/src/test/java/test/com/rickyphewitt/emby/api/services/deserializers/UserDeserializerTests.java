package test.com.rickyphewitt.emby.api.services.deserializers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.services.deserializers.ArtistDeserializer;

import test.com.rickyphewitt.emby.api.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserDeserializerTests {

	@Autowired
	private ArtistDeserializer artistDeserializer;
	
	
	@Mock
	private JsonDeserializationContext jsonDeserializationContext;
	
	@Test
	public void deseralizeUser() {
		JsonObject jsonArtist = new JsonObject();
		jsonArtist.addProperty(EmbyJsonConstants.ID, "anyIdHere");
		jsonArtist.addProperty(EmbyJsonConstants.NAME, "Four Year Strong");
		jsonArtist.addProperty(EmbyJsonConstants.ITEM_TYPE, "Music Artist");
		jsonArtist.addProperty(EmbyJsonConstants.ARTIST_IS_FOLDER, true);
		
		
		Artist a = artistDeserializer.deserialize(jsonArtist, Artist.class, jsonDeserializationContext);
		
		Assert.assertEquals(jsonArtist.get(EmbyJsonConstants.ID).getAsString(), a.getId());
		Assert.assertEquals(jsonArtist.get(EmbyJsonConstants.NAME).getAsString(), a.getName());
		Assert.assertEquals(jsonArtist.get(EmbyJsonConstants.ITEM_TYPE).getAsString(), a.getType());
		Assert.assertEquals(jsonArtist.get(EmbyJsonConstants.ARTIST_IS_FOLDER).getAsBoolean(), a.getIsFolder());
		
	}
	
	
	
}
