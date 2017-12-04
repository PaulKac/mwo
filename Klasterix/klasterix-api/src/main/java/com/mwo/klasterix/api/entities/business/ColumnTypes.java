package com.mwo.klasterix.api.entities.business;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ColumnTypes {
	STRING, NUMBER, DATE;

	@JsonCreator
	public static ColumnTypes fromString(String key) {
		for (ColumnTypes type : ColumnTypes.values()) {
			if (type.name().equalsIgnoreCase(key))
				return type;
		}
		return null;
	}
}
