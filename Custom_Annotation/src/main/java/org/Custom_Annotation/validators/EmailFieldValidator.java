package org.Custom_Annotation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation for email field
 * @author priyambadam
 *
 */
public class EmailFieldValidator implements FieldValidator {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	@Override
	public boolean validate(String value) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		return matcher.find();
	}

}
