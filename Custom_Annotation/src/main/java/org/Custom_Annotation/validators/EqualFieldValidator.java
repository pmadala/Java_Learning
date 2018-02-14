package org.Custom_Annotation.validators;

import java.lang.reflect.Field;

import org.Custom_Annotation.model.Document;

/**
 * Validation for cross field validations
 * @author priyambadam
 *
 */
public class EqualFieldValidator implements FieldValidator {

	String baseField;
	Class<? extends Document> matchClass;
	String matchField;

	public void initialise(String baseField, Class<? extends Document> matchClass, String matchField) {
		this.baseField = baseField;
		this.matchClass = matchClass;
		this.matchField = matchField;

	}

	public boolean validateFields(String value,Document matchObject) {
		try {
			Object baseFieldValue = value;
			Object matchFieldValue = getFieldValue(matchObject, matchField);
			return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
		} catch (Exception e) {
			// log error
			return false;
		}
	}

	private Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		Field passwordField = clazz.getDeclaredField(fieldName);
		passwordField.setAccessible(true);
		return passwordField.get(object);
	}
	
	@Override
	public boolean isEqualFieldValidator() {return true;}

	@Override
	public boolean validate(String value) {
		return false;
	}

}
