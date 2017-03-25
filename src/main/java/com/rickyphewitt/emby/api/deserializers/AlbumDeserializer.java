package com.rickyphewitt.emby.api.deserializers;

import java.lang.reflect.Type;

import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.data.Album;

@Service
public class AlbumDeserializer implements JsonDeserializer<Album>{

	@Override
	public Album deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
	
		Album album = new Album();
		
		// Parse out elements
		JsonObject jsonObj = json.getAsJsonObject();
				
				
		JsonElement jsonVal = jsonObj.get(EmbyJsonConstants.ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setId(jsonVal.getAsString());
		}		
		
		jsonVal = jsonObj.get(EmbyJsonConstants.NAME);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setName(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.SERVER_ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setServerId(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.ITEM_TYPE);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setType(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.ARTIST_IS_FOLDER);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setIsFolder(jsonVal.getAsBoolean());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.ARTIST_RUN_TIME_TICKS);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setRunTimeTicks(jsonVal.getAsInt());
		}
		
		return album;
	}
}