package org.Custom_Annotation.validators;

/**
 * An interface representing the type of validations implemented 
 * 
 * @author priyambadam
 *
 */
public interface FieldValidator {

	public default boolean isEqualFieldValidator() {return false;}

	public boolean validate(String value);
}
