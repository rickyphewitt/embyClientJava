package com.rickyphewitt.emby.api.services.deserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rickyphewitt.emby.api.data.Item;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;

public class DeserializerHelper {

	public static boolean isNull(JsonElement jsonElement) {
		boolean isNull = true;
		
		if(jsonElement != null && !jsonElement.isJsonNull()) {
			isNull = false;
		}
	
		return isNull;
	}
	
}
