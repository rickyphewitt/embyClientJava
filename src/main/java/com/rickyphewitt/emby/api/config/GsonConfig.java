package com.rickyphewitt.emby.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.api.data.User;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.api.deserializers.AlbumDeserializer;
import com.rickyphewitt.emby.api.deserializers.AlbumSetDeserializer;
import com.rickyphewitt.emby.api.deserializers.ArtistDeserializer;
import com.rickyphewitt.emby.api.deserializers.ArtistSetDeserializer;
import com.rickyphewitt.emby.api.deserializers.AuthenticationResultDeserializer;
import com.rickyphewitt.emby.api.deserializers.PublicServerInfoDeserializer;
import com.rickyphewitt.emby.api.deserializers.SongDeserializer;
import com.rickyphewitt.emby.api.deserializers.SongSetDeserializer;
import com.rickyphewitt.emby.api.deserializers.UserDeserializer;
import com.rickyphewitt.emby.api.deserializers.UserSetDeserializer;

@Configuration
public class GsonConfig {

	@Autowired
	UserDeserializer userDeserializer;
	
	@Autowired
	AuthenticationResultDeserializer authenticationResultDeserializer;
	
	@Autowired
	ArtistDeserializer artistDeserializer;
	
	@Autowired
	AlbumDeserializer albumDeserializer;
	
	@Autowired
	SongDeserializer songDeserializer;
	
	@Autowired
	ArtistSetDeserializer artistSetDeserializer;
	
	@Autowired
	AlbumSetDeserializer albumSetDeserializer;

	@Autowired
	SongSetDeserializer songSetDeserializer;
	
	@Autowired
	PublicServerInfoDeserializer publicServerInfoDeserializer;
	
	@Autowired
	UserSetDeserializer userSetDeserializer;
	
	@Bean
	public Gson customGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(User.class, userDeserializer);
		gsonBuilder.registerTypeAdapter(UserSet.class, userSetDeserializer);
		gsonBuilder.registerTypeAdapter(AuthenticationResult.class, authenticationResultDeserializer);
		gsonBuilder.registerTypeAdapter(Artist.class, artistDeserializer);
		gsonBuilder.registerTypeAdapter(Album.class, albumDeserializer);
		gsonBuilder.registerTypeAdapter(Song.class, songDeserializer);
		gsonBuilder.registerTypeAdapter(ArtistSet.class, artistSetDeserializer);
		gsonBuilder.registerTypeAdapter(AlbumSet.class, albumSetDeserializer);
		gsonBuilder.registerTypeAdapter(SongSet.class, songSetDeserializer);
		gsonBuilder.registerTypeAdapter(PublicServerInfo.class, publicServerInfoDeserializer);
		
	
		return gsonBuilder.create();
	}
}
