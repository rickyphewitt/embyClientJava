package test.com.rickyphewitt.emby.api.services.deserializers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.services.deserializers.UserDeserializer;

import test.com.rickyphewitt.emby.api.services.config.TestConfig;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ArtistDeserializerTests {

	@Autowired
	private UserDeserializer userDeserializer;
	
	
	@Mock
	private JsonDeserializationContext jsonDeserializationContext;
	
	@Test
	public void deseralizeUser() {
		JsonObject jsonUser = new JsonObject();
		jsonUser.addProperty(EmbyJsonConstants.ID, "anyIdHere");
		jsonUser.addProperty(EmbyJsonConstants.NAME, "Randy");
		
		User u = userDeserializer.deserialize(jsonUser, User.class, jsonDeserializationContext);
		
		Assert.assertEquals(jsonUser.get(EmbyJsonConstants.ID).getAsString(), u.getId());
		Assert.assertEquals(jsonUser.get(EmbyJsonConstants.NAME).getAsString(), u.getName());
		
	}
	
	
	
}
