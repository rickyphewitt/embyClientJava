package com.rickyphewitt.emby.api.services.http.query.params;

import com.rickyphewitt.emby.api.services.constants.EmbyQueryParameterConstants;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class PrimaryImageQueryParams implements QueryParams {

    private String primaryTag;

    public PrimaryImageQueryParams() {}
    public PrimaryImageQueryParams(String primaryTag) {
        this.primaryTag = primaryTag;
    }

    @Override
    public MultiValueMap<String, String> getQueryParams() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();

        queryParams.set(EmbyQueryParameterConstants.TAG, this.primaryTag);
        queryParams.set(EmbyQueryParameterConstants.MAX_HEIGHT, "360");
        queryParams.set(EmbyQueryParameterConstants.QUALITY, "90");

        return queryParams;    }
}
