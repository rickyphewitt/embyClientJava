package com.rickyphewitt.emby.api.deserializers;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.wrappers.AuthenticationResult;

@Service
public class AuthenticationResultDeserializer implements JsonDeserializer<AuthenticationResult>{

	@Autowired
	private UserDeserializer userDeserializer;
	
	@Override
	public AuthenticationResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
	
		AuthenticationResult authResult = new AuthenticationResult();

		// Parse out inner classes
		JsonObject jsonObj = json.getAsJsonObject();
		
		JsonElement jsonElement = jsonObj.get("User");
		
		if(!DeserializerHelper.isNull(jsonElement)){
			authResult.setUser(userDeserializer.deserialize(jsonElement, User.class, context));
		}
		
		jsonElement = jsonObj.get(EmbyJsonConstants.ACCESS_TOKEN);
		if(!DeserializerHelper.isNull(jsonElement)) {
			authResult.setAccessToken(jsonElement.getAsString());
		}
		
		return authResult;
	
		
		
	}

}
