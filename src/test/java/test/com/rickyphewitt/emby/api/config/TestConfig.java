package test.com.rickyphewitt.emby.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.rickyphewitt.emby.api.config.Config;
import com.rickyphewitt.emby.api.config.GsonConfig;

@Configuration
@ComponentScan({"com.rickyphewitt.emby.api"})
@Import({Config.class, GsonConfig.class})
public class TestConfig {

}

