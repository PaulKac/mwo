package com.mwo.klasterix.api.entities.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mwo.klasterix.api.Main;
import com.mwo.klasterix.api.converters.EntityConverter;
import com.mwo.klasterix.api.utils.RandomStrings;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
	@Id
	private String userId;

	@Indexed(unique = true)
	private String name;

	private String password;

	private LocalDateTime lastLoginDate;

	private String authToken; //TODO pewnie to zmienic na jakas klase

	@JsonCreator
	public static User fromString(String link) {
		EntityConverter converter = Main.bean(EntityConverter.class);
		return converter.convert(new Link(link), User.class);
	}

	public static User random() {
		User user = new User();

		user.setName(RandomStrings.letterString(5));
		user.setPassword(RandomStrings.letterString(5));
		user.setLastLoginDate(LocalDateTime.now());

		return user;
	}
}
