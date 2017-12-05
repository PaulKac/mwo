package com.mwo.klasterix.api.controllers;

import com.mwo.klasterix.api.entities.business.User;
import com.mwo.klasterix.api.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/loginToUser")
public class UserLoginController {
    private static final Logger LOG = LogManager.getLogger(UserLoginController.class);

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/{userName}")
    @ResponseBody
    public HttpEntity<String> loginToUser(@PathVariable String userName, @RequestBody List<String> loginInfo) {
        User user = userRepository.findByName(userName).orElseThrow(IllegalArgumentException::new);

        //TODO ustawienie access tokena dla usera

        return ResponseEntity.ok("Access Token for user: " + userName + " created");
    }
}
