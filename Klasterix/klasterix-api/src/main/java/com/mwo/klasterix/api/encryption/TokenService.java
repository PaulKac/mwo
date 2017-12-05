package com.mwo.klasterix.api.encryption;

import com.mwo.klasterix.api.entities.business.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TokenService {
	public String generateToken(String mongoId, LocalDateTime timestamp) {
		return mongoId + "_" + Timestamp.valueOf(timestamp).toInstant().toEpochMilli();
	}

	public boolean validateToken(String token, User user) {
		String[] split = token.split("_");

		if(split.length < 2)
			return false;

		if(!split[0].equals(user.getUserId()))
			return false;

		if(!split[1].equals(String.valueOf(Timestamp.valueOf(user.getLastLoginDate()).toInstant().toEpochMilli())))
			return false;

		return true;
	}
}

