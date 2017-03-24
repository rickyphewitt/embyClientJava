package com.rickyphewitt.emby.api.http.interceptors;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.rickyphewitt.emby.api.client.ApiV1Client;

/**
 * Adds x-emby-authorization header to each client call
 * <p>
 *
 * @see         application.properties to change these values
 */

@Component
public class XEmbyAuthorizationInterceptor implements ClientHttpRequestInterceptor {

	private static final String AUTHORIZATION = "MediaBrowser";

	@Value("${client.name}")
	private String clientName;
	
	@Value("${device.name}")
	private String deviceName;
	
	@Value("${device.id}")
	private String deviceId;
	
	@Value("${emby.version}")
	private String embyVersion;
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
        
		// E.g. MediaBrowser Client="Emby Mobile", Device="Chrome", DeviceId="18952fb30d9ac6cd8c22335dd3f043a75c62af29", Version="3.2.5.0"
		String authHeader = AUTHORIZATION + " " + 
		String.format("Client=\"%1$s\", DeviceId=\"%2$s\", Device=\"%3$s\", Version=\"%4$s\"", clientName, deviceId, deviceName, embyVersion);
		
		// check if we have a token, if so append it
		if(ApiV1Client.getAccessToken() != null) {
			authHeader += ", " + String.format("Token=\"%1$s\"", ApiV1Client.getAccessToken());
		}
		
		HttpHeaders headers = request.getHeaders();
        headers.add("x-emby-authorization", authHeader);
        return execution.execute(request, body);
        
	}

}
