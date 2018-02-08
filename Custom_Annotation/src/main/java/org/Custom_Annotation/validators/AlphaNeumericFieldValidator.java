package org.Custom_Annotation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaNeumericFieldValidator extends AbstractFieldValidator {

	public static final Pattern VALID_ALPHANEUMERIC_REGEX = Pattern.compile( "^[A-Za-z0-9]*$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean validate(String value) {
		Matcher matcher = VALID_ALPHANEUMERIC_REGEX.matcher(value);
		return matcher.find();
	}

}
