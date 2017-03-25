package com.rickyphewitt.emby.api.http.query.params;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rickyphewitt.emby.api.constants.EmbyQueryParameterConstants;

public class AlbumSetQueryParams implements QueryParams {

	private String artist;
	
	public AlbumSetQueryParams(){}

	public AlbumSetQueryParams(String artist){
		this.artist = artist;
	}
	
	@Override
	public MultiValueMap<String, String> getQueryParams() {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		
		queryParams.set(EmbyQueryParameterConstants.SORT_BY, "SortName");
		queryParams.set(EmbyQueryParameterConstants.SORT_ORDER, "Ascending");
		queryParams.set(EmbyQueryParameterConstants.INCLUDE_ITEM_TYPES, "MusicAlbum");
		queryParams.set(EmbyQueryParameterConstants.RECURSIVE, "true");
		queryParams.set(EmbyQueryParameterConstants.ARTIST_IDS, this.artist);
		
		return queryParams;
	}

}
