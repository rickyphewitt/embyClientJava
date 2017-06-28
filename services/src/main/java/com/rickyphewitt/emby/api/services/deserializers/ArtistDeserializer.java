package com.rickyphewitt.emby.api.services.deserializers;

import java.lang.reflect.Type;

import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;

@Service
public class ArtistDeserializer implements JsonDeserializer<Artist>{

	@Override
	public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
	
		Artist artist = new Artist();
		
		// Parse out elements
		JsonObject jsonObj = json.getAsJsonObject();
				
				
		JsonElement jsonVal = jsonObj.get(EmbyJsonConstants.ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			artist.setId(jsonVal.getAsString());
		}		
		
		jsonVal = jsonObj.get(EmbyJsonConstants.NAME);	
		if(!DeserializerHelper.isNull(jsonVal)){
			artist.setName(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.SERVER_ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			artist.setServerId(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.ITEM_TYPE);	
		if(!DeserializerHelper.isNull(jsonVal)){
			artist.setType(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.ARTIST_IS_FOLDER);	
		if(!DeserializerHelper.isNull(jsonVal)){
			artist.setIsFolder(jsonVal.getAsBoolean());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.MEDIA_RUN_TIME_TICKS);	
		if(!DeserializerHelper.isNull(jsonVal)){
			artist.setRunTimeTicks(jsonVal.getAsInt());
		}
		
		return artist;
	}
}
