package com.mwo.klasterix.api.controllers;

import com.mwo.klasterix.api.converters.EntityConverter;
import com.mwo.klasterix.api.entities.business.User;
import com.mwo.klasterix.api.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/users")
public class UserController {
	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityConverter converter;

	@PostMapping("/insertRandomUser")
	@ResponseBody
	public void insertUser() {
		LOG.info("Inserting random user");

		userRepository.insert(User.random());
	}
}
