package org.Custom_Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.EqualFields;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;
import org.Custom_Annotation.model.Document;
import org.Custom_Annotation.validators.EqualFieldValidator;
import org.Custom_Annotation.validators.FieldValidator;
import org.Custom_Annotation.validators.FieldValidatorFactory;

public enum AnnotationRunnerHelper {
	INSTANCE;
	
	
	public static void validateFieldAnnotations(List<String> errorMessages, Document doc) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
		Class classObj = doc.getClass();
		Annotation aadharAnnottaion = classObj.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, doc, classObj, aadharAnnottaion);
	}


	/**
	 * Invoke annotations on each field
	 * 
	 * @param errorMessages
	 * @param doc
	 * @param classObj
	 * @param annotations
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	private static void validateFieldAnnotations(List<String> errorMessages, Document doc, Class classObj,
			Annotation annotations)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
		if (!Objects.isNull(annotations)) {
			Field[] fields = classObj.getDeclaredFields();
			for (Field field : fields) {
				CheckFor checkForAnnotation = (CheckFor) field.getAnnotation(CheckFor.class);
				if (!Objects.isNull(checkForAnnotation)) {
					checkvalidations(errorMessages, doc, classObj, field, checkForAnnotation);
				}
			}
		}
		return;
	}


	private static void checkvalidations(List<String> errorMessages, Document doc, Class classObj, Field field,
			CheckFor checkForAnnotation)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
		ValidationType[] validationTypes = checkForAnnotation.type();
		for (ValidationType type : validationTypes) {
			Method method = getValueForField(field.getName(), classObj);
			Object object = method.invoke(doc);
			String value = Objects.isNull(object)? "" : object.toString();
			if (!validateByType(type, value,field, doc)) {
				errorMessages.add("\nValidation failed for fields : " + field.getName());
			}
		}
	}


	private static boolean validateByType(ValidationType type, String valueForField, Field field, Document doc) throws Exception {
		FieldValidator validator = ((FieldValidator) FieldValidatorFactory.getInstance(type));
		return validator.validate(valueForField);
	}

	private static Method getValueForField(String fieldName, Class classInst) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder methodName = new StringBuilder("get");
		methodName.append(fieldName.substring(0, 1).toUpperCase());
		methodName.append(fieldName.substring(1, fieldName.length()));
		Method method = classInst.getMethod(methodName.toString(), new Class[] {});
		return method;
	}
	
	private static boolean validateEqualFields(Document doc1, Document doc2) throws Exception {
		Class doc1Class = doc1.getClass();
		Annotation doc1Annotations = doc1Class.getAnnotation(Validate.class);
		if (!Objects.isNull(doc1Annotations)) {
			Field[] fields = doc1Class.getDeclaredFields();
			
			for (Field field : fields) {
				
				CheckFor checkForAnnotation = (CheckFor) field.getAnnotation(CheckFor.class);

				if (!Objects.isNull(checkForAnnotation)) {
					ValidationType[] validationTypes = checkForAnnotation.type();

					for (ValidationType type : validationTypes) {
						Method method = getValueForField(field.getName(), doc1Class);
						Object object = method.invoke(doc1);
						String value = Objects.isNull(object)? "" : object.toString();
						FieldValidator validator = ((FieldValidator) FieldValidatorFactory.getInstance(type));
						if (validator.isEqualFieldValidator()) {
							EqualFieldValidator equalFieldValidator = (EqualFieldValidator) validator;
							EqualFields equalFieldAnnotation = field.getAnnotation(EqualFields.class);
							if (!Objects.isNull(equalFieldAnnotation)) {
								equalFieldValidator.initialise(value, equalFieldAnnotation.matchClass(), equalFieldAnnotation.matchField());			
								return equalFieldValidator.validateFields(value, doc2);
							}
						}
					}
				}
			}
		}
		return false;		
	}
}
