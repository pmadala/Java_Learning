package org.Custom_Annotation.validators;

public class AddressLengthValidator  extends NumberLengthValidator{
	public boolean validate(String value) {
		return validateLength(value, 100);
	}
	@Override
	protected boolean validateLength(String value, int length) {
		return value.length() <= length ;
	}
}
