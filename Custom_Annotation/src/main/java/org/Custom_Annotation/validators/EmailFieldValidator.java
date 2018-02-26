package org.Custom_Annotation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation for email field
 * @author priyambadam
 *
 */
public class EmailFieldValidator implements FieldValidator {

	public static Pattern VALID_EMAIL_ADDRESS_REGEX;
	
	public EmailFieldValidator(String value) {
		VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
	}

	@Override
	public boolean validate(String value) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		return matcher.find();
	}

}
