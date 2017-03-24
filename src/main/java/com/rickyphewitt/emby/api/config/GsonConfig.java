package com.rickyphewitt.emby.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.ItemSet;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.wrappers.AuthenticationResult;
import com.rickyphewitt.emby.api.deserializers.ArtistDeserializer;
import com.rickyphewitt.emby.api.deserializers.ArtistSetDeserializer;
import com.rickyphewitt.emby.api.deserializers.AuthenticationResultDeserializer;
import com.rickyphewitt.emby.api.deserializers.ItemSetDeserializer;
import com.rickyphewitt.emby.api.deserializers.UserDeserializer;

@Configuration
public class GsonConfig {

	@Autowired
	UserDeserializer userDeserializer;
	
	@Autowired
	AuthenticationResultDeserializer authenticationResultDeserializer;
	
	@Autowired
	ArtistDeserializer artistDeserializer;
	
	@Autowired
	ArtistSetDeserializer artistSetDeserializer;
	
	@Bean
	public Gson customGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(User.class, userDeserializer);
		gsonBuilder.registerTypeAdapter(AuthenticationResult.class, authenticationResultDeserializer);
		gsonBuilder.registerTypeAdapter(Artist.class, artistDeserializer);
		gsonBuilder.registerTypeAdapter(ArtistSet.class, artistSetDeserializer);
	
		return gsonBuilder.create();
	}
}
