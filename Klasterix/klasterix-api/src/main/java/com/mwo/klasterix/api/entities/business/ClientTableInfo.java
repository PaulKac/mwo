package com.mwo.klasterix.api.entities.business;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ClientTableInfo {
	@Id
	private String infoId;

	@DBRef
	private User user;

	@Indexed(unique = true)
	private String tableName;

	@DBRef
	private List<Column> columns;

	private LocalDateTime creationTime;

	private LocalDateTime lastUpdateTime;

	private LocalDateTime lastQueryTime;

	private Integer updateCount;

	private Integer queryCount;
}