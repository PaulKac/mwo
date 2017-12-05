package com.mwo.klasterix.api.controllers;

import com.mwo.klasterix.api.encryption.DecryptionService;
import com.mwo.klasterix.api.entities.business.User;
import com.mwo.klasterix.api.repositories.UserRepository;
import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/register")
public class UserRegisterController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DecryptionService decryptionService;

	@PostMapping
	@ResponseBody
	public HttpEntity<String> register(@RequestBody User user) {
		Optional<User> userEntity = userRepository.findByName(user.getName());

		if(userEntity.isPresent())
			return ResponseEntity.status(Response.SC_CONFLICT).build();

		if(StringUtils.isAnyBlank(user.getName(), user.getPassword()))
			return ResponseEntity.status(Response.SC_BAD_REQUEST).build();

		String decryptedHashed = StringUtils.EMPTY;
		try {
			decryptedHashed = decryptionService.decryptThenHash(user.getPassword());
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).build();
		}

		user.setPassword(decryptedHashed);
		user.setLastLoginDate(LocalDateTime.now());
		User newUser = userRepository.insert(user);

		return ResponseEntity.ok(newUser.getUserId());
	}
}