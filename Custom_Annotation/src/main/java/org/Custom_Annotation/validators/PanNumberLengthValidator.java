package org.Custom_Annotation.validators;

public class PanNumberLengthValidator  extends NumberLengthValidator{
	public boolean validate(String value) {
		return validateLength(value, 10);
	}
}
