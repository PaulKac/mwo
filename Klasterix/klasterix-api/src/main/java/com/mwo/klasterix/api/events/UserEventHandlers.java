package com.mwo.klasterix.api.events;

import com.mwo.klasterix.api.entities.business.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.time.LocalDateTime;

@RepositoryEventHandler
public class UserEventHandlers {
	private static final Logger LOG = LogManager.getLogger(UserEventHandlers.class);

	@HandleBeforeCreate(User.class)
	public void handleUserCreate(User user) {
		LOG.info("Handling user before create: " + user);

		user.setLastLoginDate(LocalDateTime.now());
	}
}
