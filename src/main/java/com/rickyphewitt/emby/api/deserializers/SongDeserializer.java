package com.rickyphewitt.emby.api.deserializers;

import java.lang.reflect.Type;

import org.springframework.stereotype.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.data.Song;

@Service
public class SongDeserializer implements JsonDeserializer<Song>{

	@Override
	public Song deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
	
		Song song = new Song();
		
		// Parse out elements
		JsonObject jsonObj = json.getAsJsonObject();
				
				
		JsonElement jsonVal = jsonObj.get(EmbyJsonConstants.ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setId(jsonVal.getAsString());
		}		
		
		jsonVal = jsonObj.get(EmbyJsonConstants.NAME);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setName(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.SERVER_ID);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setServerId(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.ITEM_TYPE);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setType(jsonVal.getAsString());
		}	
		
		jsonVal = jsonObj.get(EmbyJsonConstants.SONG_CONTAINER);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setContainer(jsonVal.getAsString());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.MEDIA_RUN_TIME_TICKS);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setRunTimeTicks(jsonVal.getAsInt());
		}
		
		jsonVal = jsonObj.get(EmbyJsonConstants.SONG_TRACK_NUMBER);	
		if(!DeserializerHelper.isNull(jsonVal)){
			song.setTrackNumber(jsonVal.getAsInt());
		}
		
		return song;
	}
}
