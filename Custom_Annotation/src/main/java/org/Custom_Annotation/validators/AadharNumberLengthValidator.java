package org.Custom_Annotation.validators;

public class AadharNumberLengthValidator extends NumberLengthValidator{
	public boolean validate(String value) {
		return validateLength(value, 11);
	}
}
