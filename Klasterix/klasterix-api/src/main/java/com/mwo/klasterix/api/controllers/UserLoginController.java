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

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/login")
public class UserLoginController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DecryptionService decryptionService;

	@PostMapping
	@ResponseBody
	public HttpEntity<String> loginToUser(@RequestBody User user) {
		String decrypted = StringUtils.EMPTY;
		try {
			decrypted = decryptionService.decrypt(user.getPassword());
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
		}

		User userEntity = userRepository.findByName(user.getName()).orElseThrow(IllegalArgumentException::new);

		try {
			if (!decryptionService.authenticate(decrypted, userEntity.getPassword()))
				return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
		}

		return ResponseEntity.ok(userEntity.getUserId());
	}
}
