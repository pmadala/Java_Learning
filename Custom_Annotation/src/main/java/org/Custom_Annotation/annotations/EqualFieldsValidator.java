package org.Custom_Annotation.annotations;

import java.lang.reflect.Field;

import org.Custom_Annotation.model.Document;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {
	 
    private String baseField;
    private String matchField;
    private Class<? extends Document> matchClass;
 
    @Override
    public void initialize(EqualFields constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
        matchClass = constraint.matchClass();
    }
 
    @Override
    public boolean isValid(Object object, ConstraintValidator context) {
        try {
            Object baseFieldValue = getFieldValue(object, baseField);
            Object matchFieldValue = getFieldValue(matchClass, matchField);
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
 
}