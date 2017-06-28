package com.rickyphewitt.emby.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.rickyphewitt.emby.api.services.config.Config;

@Import({Config.class})
@SpringBootApplication
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
    
}
