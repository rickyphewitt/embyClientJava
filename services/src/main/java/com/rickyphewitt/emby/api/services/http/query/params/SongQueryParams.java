package com.rickyphewitt.emby.api.services.http.query.params;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rickyphewitt.emby.api.services.constants.EmbyQueryParameterConstants;

public class SongQueryParams implements QueryParams {

	@Override
	public MultiValueMap<String, String> getQueryParams() {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		
		queryParams.set(EmbyQueryParameterConstants.STATIC, "true");
		
		return queryParams;
	}

}
