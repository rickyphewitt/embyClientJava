package com.rickyphewitt.emby.api.http.query.params;

import org.springframework.util.MultiValueMap;

public interface QueryParams {

	MultiValueMap<String, String> getQueryParams();
}
