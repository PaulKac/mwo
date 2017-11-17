package com.mwo.klasterix.api.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class Validation {
	public static boolean noBlanks(String... args) {
		return Arrays.stream(args).noneMatch(StringUtils::isBlank);
	}

	public static boolean anyBlank(String... args) {
		return Arrays.stream(args).anyMatch(StringUtils::isBlank);
	}

	public static boolean isNull(Object object) {
		return Objects.isNull(object);
	}
}
