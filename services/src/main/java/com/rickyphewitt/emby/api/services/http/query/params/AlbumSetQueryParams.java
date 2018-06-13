package com.rickyphewitt.emby.api.services.http.query.params;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rickyphewitt.emby.api.services.constants.EmbyQueryParameterConstants;

public class AlbumSetQueryParams implements QueryParams {

	private String artistId;
	
	public AlbumSetQueryParams(){}

	public AlbumSetQueryParams(String artistId){
		this.artistId = artistId;
	}
	
	@Override
	public MultiValueMap<String, String> getQueryParams() {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		
		queryParams.set(EmbyQueryParameterConstants.SORT_BY, "SortName");
		queryParams.set(EmbyQueryParameterConstants.SORT_ORDER, "Ascending");
		queryParams.set(EmbyQueryParameterConstants.INCLUDE_ITEM_TYPES, "MusicAlbum");
		queryParams.set(EmbyQueryParameterConstants.RECURSIVE, "true");
		if(this.artistId != null) {
			queryParams.set(EmbyQueryParameterConstants.ARTIST_IDS, this.artistId);
		}

		
		return queryParams;
	}

}
