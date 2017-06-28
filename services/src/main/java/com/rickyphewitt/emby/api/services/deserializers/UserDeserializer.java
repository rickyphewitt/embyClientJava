package com.rickyphewitt.emby.api.services.deserializers;

import java.lang.reflect.Type;

import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;

@Service
public class UserDeserializer implements JsonDeserializer<User> {

	@Override
	public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		User user = new User();
		
		// Parse out elements
		JsonObject jsonObj = json.getAsJsonObject();
		
		
		JsonElement jsonVal = jsonObj.get(EmbyJsonConstants.ID);
		
		if(!DeserializerHelper.isNull(jsonVal)) {
			user.setId(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.NAME);
		if(!DeserializerHelper.isNull(jsonVal)) {
			user.setName(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.USER_HAS_PASSWORD);
		if(!DeserializerHelper.isNull(jsonVal)) {
			user.setHasPassword(jsonVal.getAsBoolean());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.USER_ENABLE_AUTOLOGIN);
		if(!DeserializerHelper.isNull(jsonVal)) {
			user.setEnableAutoLogin(jsonVal.getAsBoolean());
		}

		jsonVal = jsonObj.get(EmbyJsonConstants.SERVER_ID);
		if(!DeserializerHelper.isNull(jsonVal)) {
			user.setServerId(jsonVal.getAsString());
		}
		
		return user;
	}
	

}
