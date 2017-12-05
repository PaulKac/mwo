package com.mwo.klasterix.api.converters;

import com.mwo.klasterix.api.entities.business.User;
import org.springframework.stereotype.Service;

@Service
public class CollectionNameConverter {
	private static final String TABLE_NAME_PATTERN = "%s_%s";

	public String resolveCollectionName(User user, String tableName) {
		return String.format(TABLE_NAME_PATTERN, user.getName(), tableName);
	}
}
