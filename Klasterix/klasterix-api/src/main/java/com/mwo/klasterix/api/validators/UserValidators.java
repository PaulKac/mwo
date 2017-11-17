package com.mwo.klasterix.api.validators;

import com.mwo.klasterix.api.entities.business.User;
import com.mwo.klasterix.api.utils.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidators {
	private static final Logger LOG = LogManager.getLogger(UserValidators.class);

	@Component("beforeCreateUserValidator")
	public static class UserBeforeCreateValidator implements Validator {
		@Override
		public boolean supports(Class<?> aClass) {
			return User.class.equals(aClass);
		}

		@Override
		public void validate(Object object, Errors errors) {
			User user = (User) object;

			LOG.info("Validating user before create: " + user);

			if (Validation.anyBlank(user.getName()))
				errors.rejectValue("name", "name.empty");
			else if (Validation.anyBlank(user.getPassword()))
				errors.rejectValue("password", "password.empty");
		}
	}

	@Component("beforeSaveUserValidator")
	public static class UserBeforeSaveValidator implements Validator {
		@Override
		public boolean supports(Class<?> aClass) {
			return User.class.equals(aClass);
		}

		@Override
		public void validate(Object object, Errors errors) {
			User user = (User) object;

			LOG.info("Validating user before save: " + user);

			if (Validation.anyBlank(user.getName()))
				errors.rejectValue("name", "name.empty");
			else if (Validation.anyBlank(user.getPassword()))
				errors.rejectValue("password", "password.empty");
		}
	}
}
