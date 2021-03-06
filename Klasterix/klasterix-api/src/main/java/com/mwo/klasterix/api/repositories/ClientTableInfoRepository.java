package com.mwo.klasterix.api.repositories;

import com.mwo.klasterix.api.entities.business.ClientTableInfo;
import com.mwo.klasterix.api.entities.business.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path="tables")
public interface ClientTableInfoRepository extends MongoRepository<ClientTableInfo, String> {
	List<ClientTableInfo> findByUser(User user);

	List<ClientTableInfo> findByUserName(String userName);
}
