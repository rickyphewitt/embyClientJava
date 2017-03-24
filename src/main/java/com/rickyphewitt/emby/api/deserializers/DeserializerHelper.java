package com.rickyphewitt.emby.api.deserializers;

import com.google.gson.JsonElement;

public class DeserializerHelper {

	public static boolean isNull(JsonElement jsonElement) {
		boolean isNull = true;
		
		if(jsonElement != null && !jsonElement.isJsonNull()) {
			isNull = false;
		}
	
		return isNull;
	}
	
}
