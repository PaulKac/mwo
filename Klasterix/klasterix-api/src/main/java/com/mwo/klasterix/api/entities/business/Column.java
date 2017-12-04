package com.mwo.klasterix.api.entities.business;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Column {
	@Id
	private String columnId;

	@Indexed
	private String columnName;

	private ColumnTypes columnType;
}
