package com.mwo.klasterix.api.controllers;

import com.google.common.collect.Sets;
import com.mwo.klasterix.api.entities.business.ClientTableInfo;
import com.mwo.klasterix.api.entities.business.User;
import com.mwo.klasterix.api.repositories.ClientTableInfoRepository;
import com.mwo.klasterix.api.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
@RequestMapping("/create")
public class CreateTableController {
	private static final Logger LOG = LogManager.getLogger(CreateTableController.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientTableInfoRepository clientTableInfoRepository;

	@PostMapping("/{userName}/{tableName}")
	@ResponseBody
	public HttpEntity<String> createTable(@PathVariable String userName, @PathVariable String tableName, @RequestBody List<String> columnNames) {
		User user = userRepository.findByName(userName).orElseThrow(IllegalArgumentException::new);

		List<ClientTableInfo> userTables = clientTableInfoRepository.findByUser(user);

		if (userTables.stream().map(ClientTableInfo::getTableName).anyMatch(item -> item.equals(tableName)))
			return new ResponseEntity<>("Table with that name already exists!", HttpStatus.BAD_REQUEST);

		if(Sets.newHashSet(columnNames).size() != columnNames.size())
			return new ResponseEntity<>("Table cannot have duplicated columns!", HttpStatus.BAD_REQUEST);

		LOG.info("Creating table: " + tableName + " for user: " + userName);

		mongoTemplate.createCollection(tableName);

		ClientTableInfo tableInfo = ClientTableInfo.builder().tableName(tableName).user(user).columnNames(columnNames).build();
		clientTableInfoRepository.insert(tableInfo);

		return ResponseEntity.ok("Table " + tableName + " created");
	}
}

