package com.rickyphewitt.emby.api.services.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.rickyphewitt.emby.api.services.http.interceptors.XEmbyAuthorizationInterceptor;

@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan({"com.rickyphewitt.emby.api.services"})
@Import(GsonConfig.class)
public class Config {

	@Autowired
	XEmbyAuthorizationInterceptor xEmbyAuthorizationInterceptor;
	
	@Autowired
	Gson customGson;
	
	@Bean
	public RestTemplate restTemplate() {
		
		// Build up gson message convertor and set custom gson
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
		GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
		gsonHttpMessageConverter.setGson(customGson);
		messageConverters.add(gsonHttpMessageConverter);
		messageConverters.add(resourceHttpMessageConverter);
		messageConverters.add(new ByteArrayHttpMessageConverter());
	
		// Build restTemplate with message converters and interceptors
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		restTemplate.setMessageConverters(messageConverters);
	    restTemplate.setInterceptors(Collections.singletonList(xEmbyAuthorizationInterceptor));
	    return restTemplate;
	}
    
	private ClientHttpRequestFactory clientHttpRequestFactory() {
	    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	    factory.setReadTimeout(2000);
	    factory.setConnectTimeout(2000);
	    return factory;
	}
}