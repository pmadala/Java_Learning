package org.Custom_Annotation.validators;

/**
 * Validation for aadhar number
 * @author priyambadam
 *
 */
public class AadharNumberLengthValidator extends NumberLengthValidator{
	public boolean validate(String value) {
		return validateLength(value, 11);
	}
}
