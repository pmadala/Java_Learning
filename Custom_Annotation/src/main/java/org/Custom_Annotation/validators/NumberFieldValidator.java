package org.Custom_Annotation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFieldValidator extends AbstractFieldValidator {

	public static final Pattern VALID_NUMBER_REGEX = Pattern.compile("^(0|[1-9][0-9]*)$" , Pattern.CASE_INSENSITIVE);

	@Override
	public boolean validate(String value) {
		Matcher matcher = VALID_NUMBER_REGEX.matcher(value);
		return matcher.find();
	}

}
