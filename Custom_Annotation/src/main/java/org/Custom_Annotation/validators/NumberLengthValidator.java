package org.Custom_Annotation.validators;

public abstract class NumberLengthValidator extends AbstractFieldValidator{

	protected boolean validateLength(String value, int length) {
		return value.length() == length ;
	}

}

