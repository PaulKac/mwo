package com.mwo.klasterix.api.configs.repositories;

import com.mwo.klasterix.api.events.UserEventHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

	@Bean
	UserEventHandlers userEventHandlers() {
		return new UserEventHandlers();
	}

}
