package com.mwo.klasterix.api.repositories;

import com.mwo.klasterix.api.entities.business.ClientTableInfo;
import com.mwo.klasterix.api.entities.business.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientTableInfoRepository extends MongoRepository<ClientTableInfo, String> {
    Optional<ClientTableInfo> findByNameIgnoreCase(String name);
}
