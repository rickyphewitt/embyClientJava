package com.rickyphewitt.emby.api.deserializers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.UserSet;

@Service
public class UserSetDeserializer implements JsonDeserializer<UserSet> {

	@Override
	public UserSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		

		UserSet userSet = new UserSet();
		
		JsonArray jsonItems = json.getAsJsonArray();
		//JsonArray jsonItems = jsonObj.getAsJsonArray(EmbyJsonConstants.ITEMS);
		
		if(!DeserializerHelper.isNull(jsonItems)) {
			ArrayList<User> items = new ArrayList<User>();
			
			for(JsonElement jsonElement: jsonItems) {
				items.add((User)context.deserialize(jsonElement, User.class));
			}
			
			userSet.setItems(items);			
		}

		return userSet;
	}
	

}
