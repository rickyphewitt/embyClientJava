package com.rickyphewitt.emby.api.services.deserializers;

import java.lang.reflect.Type;

import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;

@Service
public class PublicServerInfoDeserializer implements JsonDeserializer<PublicServerInfo>{

	@Override
	public PublicServerInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
	
		PublicServerInfo publicServerInfo = new PublicServerInfo();
		
		// Parse out elements
		JsonObject jsonObj = json.getAsJsonObject();
				
				
		JsonElement jsonVal = jsonObj.get(EmbyJsonConstants.ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			publicServerInfo.setId(jsonVal.getAsString());
		}		
		
		jsonVal = jsonObj.get(EmbyJsonConstants.PUBLIC_SERVER_LOCAL_ADDRESS);	
		if(!DeserializerHelper.isNull(jsonVal)){
			publicServerInfo.setLocalAddress(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.PUBLIC_SERVER_OPERATING_SYSTEM);	
		if(!DeserializerHelper.isNull(jsonVal)){
			publicServerInfo.setOperatingSystem(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.PUBLIC_SERVER_SERVER_NAME);	
		if(!DeserializerHelper.isNull(jsonVal)){
			publicServerInfo.setServerName(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.PUBLIC_SERVER_VERSION);	
		if(!DeserializerHelper.isNull(jsonVal)){
			publicServerInfo.setVersion(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.PUBLIC_SERVER_WAN_ADDRESS);	
		if(!DeserializerHelper.isNull(jsonVal)){
			publicServerInfo.setWanAddress(jsonVal.getAsString());
		}
		
		return publicServerInfo;
	}
}
