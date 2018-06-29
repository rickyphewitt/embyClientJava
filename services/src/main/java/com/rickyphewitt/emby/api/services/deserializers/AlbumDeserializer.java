package com.rickyphewitt.emby.api.services.deserializers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.rickyphewitt.emby.api.data.Item;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.services.constants.EmbyJsonConstants;

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
		
		jsonVal = jsonObj.get(EmbyJsonConstants.MEDIA_RUN_TIME_TICKS);	
		if(!DeserializerHelper.isNull(jsonVal)){
			album.setRunTimeTicks(jsonVal.getAsInt());
		}

		List<Item> albumsArtists = new ArrayList<Item>();
		JsonArray jsonArray = jsonObj.getAsJsonArray(EmbyJsonConstants.ALBUM_ARTISTS);
		if(jsonArray != null && jsonArray.size() > 0) {

			for(JsonElement jsonElement: jsonArray) {
				JsonObject obj = jsonElement.getAsJsonObject();
				Item item = new Item();
				item.setName(obj.get(EmbyJsonConstants.NAME).getAsString());
				item.setId(obj.get(EmbyJsonConstants.ID).getAsString());
				albumsArtists.add(item);
			}
		}

		album.setAlbumArtists(albumsArtists);

		JsonObject image_tags = jsonObj.getAsJsonObject(EmbyJsonConstants.IMAGE_TAGS);
		if(!DeserializerHelper.isNull(image_tags)){
			jsonVal = image_tags.get(EmbyJsonConstants.IMAGE_PRIMARY);
			if(!DeserializerHelper.isNull(jsonVal)) {
				album.setPrimaryImage(jsonVal.getAsString());
				Map<String, String> images = new HashMap<String, String>();
				images.put(EmbyJsonConstants.IMAGE_PRIMARY, jsonVal.getAsString());
				album.setImageTags(images);
			}
		}
		
		return album;
	}
}
