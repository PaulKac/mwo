package com.mwo.klasterix.api.entities.business;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ClientTableInfo {
    @Id
    private String clientId;

    @Indexed(unique = true)
    private String name;

    private String        tableName;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime lastQuerryTime;
    private Integer       updateCount;
    private Integer       querryCount;

}