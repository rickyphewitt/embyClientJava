package com.rickyphewitt.emby.api.http.query.params;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rickyphewitt.emby.api.constants.EmbyQueryParameterConstants;

public class SongSetQueryParams implements QueryParams {

	private String albumId;
	
	public SongSetQueryParams(){}

	public SongSetQueryParams(String albumId){
		this.albumId = albumId;
	}
	
	@Override
	public MultiValueMap<String, String> getQueryParams() {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		
		queryParams.set(EmbyQueryParameterConstants.SORT_BY, "SortName");
		queryParams.set(EmbyQueryParameterConstants.PARENT_ID, this.albumId);
		
		return queryParams;
	}

}
