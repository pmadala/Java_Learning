/**
 * 
 */
package org.Custom_Annotation.annotations;

/**
 * An enum representing all the type of validations 
 * @author priyambadam
 *
 */
public enum ValidationType {

	NOT_NULL, 
	AADHAR_NUMBER_LENGTH, 
	NUMBER, 
	ALPHANEUMERIC, 
	Gender,
	PAN_NUMBER_LENGTH, 
	ADDRESS_FIELD_LENGTH,
	DATE,
	EMAIL,
	EQUAL_FIELD;

	/*
	 * private String value;
	 * 
	 * ValidationType(final String val) { value =val; }
	 */

}
