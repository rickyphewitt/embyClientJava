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
import com.rickyphewitt.emby.api.data.ItemSet;

//@Service
public class ItemSetDeserializer<T> implements JsonDeserializer<ItemSet<T>> {
	
	private Class<T> clazz;
	
	public ItemSetDeserializer(Class<T> clazz) {
		this.clazz = clazz;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public ItemSet<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		ItemSet<T> itemSet = new ItemSet<T>();
		
		JsonObject jsonObj = json.getAsJsonObject();
		JsonArray jsonItems = jsonObj.getAsJsonArray(EmbyJsonConstants.ITEMS);
		
		if(!DeserializerHelper.isNull(jsonItems)) {
			ArrayList<T> items = new ArrayList<T>();
			
			for(JsonElement jsonElement: jsonItems) {
				items.add((T)context.deserialize(jsonElement, this.clazz));
			}
			
			itemSet.setItems(items);
			
		}

		return itemSet;
		
	}

}
