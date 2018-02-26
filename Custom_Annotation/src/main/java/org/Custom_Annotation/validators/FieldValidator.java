package org.Custom_Annotation.validators;

import org.Custom_Annotation.CustomAnnotationException;

/**
 * An interface representing the type of validations implemented 
 * 
 * @author priyambadam
 *
 */
public interface FieldValidator {

	public default boolean isEqualFieldValidator() {return false;}

	public boolean validate(String value) throws CustomAnnotationException;
}
