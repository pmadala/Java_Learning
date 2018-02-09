package org.Custom_Annotation.validators;

/**
 * Validation for pan number
 * @author priyambadam
 *
 */
public class PanNumberLengthValidator  extends NumberLengthValidator{
	public boolean validate(String value) {
		return validateLength(value, 10);
	}
}
