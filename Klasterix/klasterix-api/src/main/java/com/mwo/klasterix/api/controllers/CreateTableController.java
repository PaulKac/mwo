package com.mwo.klasterix.api.controllers;

import com.mwo.klasterix.api.converters.CollectionNameConverter;
import com.mwo.klasterix.api.entities.business.ClientTableInfo;
import com.mwo.klasterix.api.entities.business.ColumnTypes;
import com.mwo.klasterix.api.entities.business.User;
import com.mwo.klasterix.api.repositories.ClientTableInfoRepository;
import com.mwo.klasterix.api.repositories.UserRepository;
import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/create")
public class CreateTableController {
	private static final Logger LOG = LogManager.getLogger(CreateTableController.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientTableInfoRepository clientTableInfoRepository;

	@Autowired
	private CollectionNameConverter collectionNameConverter;

	@PostMapping("/{userName}/{tableName}")
	@ResponseBody
	public HttpEntity<String> createTable(@RequestHeader("Authorization") String token, @PathVariable String userName, @PathVariable String tableName, @RequestBody Map<String, ColumnTypes> columns) {
		User user = userRepository.findByName(userName).orElseThrow(IllegalArgumentException::new);

		if(!user.getUserId().equals(token))
			return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();

		String collectionName = collectionNameConverter.resolveCollectionName(user, tableName);

		List<ClientTableInfo> userTables = clientTableInfoRepository.findByUser(user);

		if (userTables.stream().map(ClientTableInfo::getCollectionName).anyMatch(item -> item.equals(collectionName)))
			return new ResponseEntity<>("Table with that name already exists!", HttpStatus.ALREADY_REPORTED);

		LOG.info("Creating table: " + collectionName + " for user: " + userName);

		mongoTemplate.createCollection(collectionName);

		ClientTableInfo tableInfo = ClientTableInfo.builder().collectionName(collectionName).user(user).columns(columns).build();
		clientTableInfoRepository.insert(tableInfo);

		return ResponseEntity.ok("Table " + tableName + " created");
	}
}

