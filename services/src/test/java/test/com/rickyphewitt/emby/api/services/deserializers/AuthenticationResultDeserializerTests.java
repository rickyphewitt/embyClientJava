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
import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.services.deserializers.AuthenticationResultDeserializer;
import com.rickyphewitt.emby.api.services.deserializers.UserDeserializer;

import test.com.rickyphewitt.emby.api.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AuthenticationResultDeserializerTests {

	@Autowired
	private UserDeserializer userDeserializer;
	
	@Autowired
	private AuthenticationResultDeserializer authResultDeserializer;
	
	@Mock
	private JsonDeserializationContext jsonDeserializationContext;
	
	@Test
	public void deseralize() {
		String accessToken = "uniqueGuidHere";
		
		JsonObject jsonUser = new JsonObject();
		jsonUser.addProperty(EmbyJsonConstants.ID, "anyIdHere");
		jsonUser.addProperty(EmbyJsonConstants.NAME, "Randy");
		
		User u = userDeserializer.deserialize(jsonUser, User.class, jsonDeserializationContext);
		
		JsonObject jsonAuthResult = new JsonObject();
		jsonAuthResult.add("User", jsonUser);
		jsonAuthResult.addProperty(EmbyJsonConstants.ACCESS_TOKEN, accessToken);
		
		AuthenticationResult authResult = authResultDeserializer.deserialize(jsonAuthResult, AuthenticationResult.class, jsonDeserializationContext);
		
		Assert.assertEquals(authResult.getUser().getId(), u.getId());
		Assert.assertEquals(authResult.getUser().getName(), u.getName());
		Assert.assertEquals(authResult.getAccessToken(), accessToken);
		
	}
	
	
	
}
