package org.Custom_Annotation.annotations;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.Custom_Annotation.model.Document;

/**
 * Custom validation representing equal field validations used for cross field
 * validation among objects of different documents
 * 
 * @author priyambadam
 *
 */
public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Document> {
	 
    private CrossFieldValidationType matchField;
    
    @Override
    public void initialize(EqualFields constraint) {
    	this.matchField = constraint.matchField();
    }
 
    @Override
    @Deprecated
    public boolean isValid(Document object, ConstraintValidatorContext context) {
       return false;
    }
 
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
 
}