package com.mwo.klasterix.api.repositories;

import com.mwo.klasterix.api.entities.business.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByNameIgnoreCase(String name);
}
