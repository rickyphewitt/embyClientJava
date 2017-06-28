package com.rickyphewitt.emby.api.services.deserializers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;

@Service
public class AlbumSetDeserializer implements JsonDeserializer<AlbumSet> {
	

	@SuppressWarnings("unchecked")
	@Override
	public AlbumSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		AlbumSet albumSet = new AlbumSet();
		
		JsonObject jsonObj = json.getAsJsonObject();
		JsonArray jsonItems = jsonObj.getAsJsonArray(EmbyJsonConstants.ITEMS);
		
		if(!DeserializerHelper.isNull(jsonItems)) {
			ArrayList<Album> items = new ArrayList<Album>();
			
			for(JsonElement jsonElement: jsonItems) {
				items.add((Album)context.deserialize(jsonElement, Album.class));
			}
			
			albumSet.setItems(items);
			
		}

		return albumSet;
		
	}

}
