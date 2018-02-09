package org.Custom_Annotation.validators;

/**
 * A skeletal representation of all the validators for length check
 * @author priyambadam
 *
 */
public abstract class NumberLengthValidator extends AbstractFieldValidator{

	protected boolean validateLength(String value, int length) {
		return value.length() == length ;
	}

}

