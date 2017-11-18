package com.mwo.klasterix.api.utils;

import org.apache.commons.text.CharacterPredicate;
import org.apache.commons.text.RandomStringGenerator;

public class RandomStrings {
	public enum CustomPredicates implements CharacterPredicate {
		ARABIC_NUMERALS {
			@Override
			public boolean test(final int codePoint) {
				return codePoint >= '0' && codePoint <= '9';
			}
		},
		ASCII_LOWERCASE_LETTERS {
			@Override
			public boolean test(final int codePoint) {
				return codePoint >= 'a' && codePoint <= 'z';
			}
		},
		ASCII_UPPERCASE_LETTERS {
			@Override
			public boolean test(final int codePoint) {
				return codePoint >= 'A' && codePoint <= 'Z';
			}
		},
		ASCII_LETTERS {
			@Override
			public boolean test(final int codePoint) {
				return ASCII_LOWERCASE_LETTERS.test(codePoint) || ASCII_UPPERCASE_LETTERS.test(codePoint);
			}
		},
		ASCII_ALPHA_NUMERALS {
			@Override
			public boolean test(final int codePoint) {
				return ASCII_LOWERCASE_LETTERS.test(codePoint) || ASCII_UPPERCASE_LETTERS.test(codePoint)
						|| ARABIC_NUMERALS.test(codePoint);
			}
		}
	}

	private static final RandomStringGenerator letterGenerator = new RandomStringGenerator.Builder().filteredBy(CustomPredicates.ASCII_LETTERS).build();

	public static final String letterString(int length) {
		return letterGenerator.generate(length);
	}
}
