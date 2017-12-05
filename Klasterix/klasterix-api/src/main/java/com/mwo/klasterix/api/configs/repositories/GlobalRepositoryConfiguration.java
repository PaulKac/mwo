package com.mwo.klasterix.api.configs.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@Configuration
public class GlobalRepositoryConfiguration extends RepositoryRestConfigurerAdapter {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.getCorsRegistry().addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
				.allowedHeaders("*")
				.exposedHeaders("WWW-Authenticate")
				.allowCredentials(true)
				.maxAge(TimeUnit.DAYS.toSeconds(1));
	}
}