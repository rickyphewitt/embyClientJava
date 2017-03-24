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
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;

@Service
public class ArtistSetDeserializer implements JsonDeserializer<ArtistSet> {
	

	@SuppressWarnings("unchecked")
	@Override
	public ArtistSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		ArtistSet artistSet = new ArtistSet();
		
		JsonObject jsonObj = json.getAsJsonObject();
		JsonArray jsonItems = jsonObj.getAsJsonArray(EmbyJsonConstants.ITEMS);
		
		if(!DeserializerHelper.isNull(jsonItems)) {
			ArrayList<Artist> items = new ArrayList<Artist>();
			
			for(JsonElement jsonElement: jsonItems) {
				items.add((Artist)context.deserialize(jsonElement, Artist.class));
			}
			
			artistSet.setItems(items);
			
		}

		return artistSet;
		
	}

}
