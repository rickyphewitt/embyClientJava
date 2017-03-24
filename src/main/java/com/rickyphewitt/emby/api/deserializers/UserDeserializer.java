package com.rickyphewitt.emby.api.deserializers;

import java.lang.reflect.Type;

import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.data.User;

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
		
		return user;
	}
	

}
