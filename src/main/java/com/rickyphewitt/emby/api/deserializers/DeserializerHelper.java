package com.rickyphewitt.emby.api.deserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rickyphewitt.emby.api.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.data.Item;

public class DeserializerHelper {

	public static boolean isNull(JsonElement jsonElement) {
		boolean isNull = true;
		
		if(jsonElement != null && !jsonElement.isJsonNull()) {
			isNull = false;
		}
	
		return isNull;
	}
	
}
