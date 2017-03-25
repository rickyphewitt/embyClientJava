package com.rickyphewitt.emby.api.deserializers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.constants.EmbyJsonConstants;
import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;

@Service
public class SongSetDeserializer implements JsonDeserializer<SongSet> {

	@Override
	public SongSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		SongSet songSet = new SongSet();
		
		JsonObject jsonObj = json.getAsJsonObject();
		JsonArray jsonItems = jsonObj.getAsJsonArray(EmbyJsonConstants.ITEMS);
		
		if(!DeserializerHelper.isNull(jsonItems)) {
			ArrayList<Song> items = new ArrayList<Song>();
			
			for(JsonElement jsonElement: jsonItems) {
				items.add((Song)context.deserialize(jsonElement, Song.class));
			}
			
			songSet.setItems(items);			
		}

		return songSet;
		
	}

}
