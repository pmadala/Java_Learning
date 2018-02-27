package org.Custom_Annotation.validators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.Custom_Annotation.CustomAnnotationException;
import org.Custom_Annotation.ReflectionUtils;
import org.Custom_Annotation.annotations.CrossFieldValidationType;
import org.Custom_Annotation.annotations.CrossValidate;
import org.Custom_Annotation.annotations.EqualFields;
import org.Custom_Annotation.model.Document;

public enum CrossValidator {
	INSTANCE;

	public Document reference;
	
	public void setReferenceDoc(Class<? extends Document> refernceClass, List<Document> documentSet) throws CustomAnnotationException {
		Optional<Document> optional = documentSet.stream().filter(doc -> doc.getClass() == refernceClass).findFirst();
		if (optional.isPresent()) {
			reference = optional.get();
		} else {
			throw new CustomAnnotationException("No documnet found in the set with refernce class type :"+ refernceClass);
		}
	}

	public void crossValidate(List<String> errorMessages, List<Document> documentSet) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Map<Field, CrossFieldValidationType> refernceConsistencyMap = fetchConsistencyCheckFields(reference);
		System.out.println("cross validation refernce doc : "+ reference.getClass().getName());
		for (Document doc : documentSet) {
			if (doc.equals(reference)) {
				continue;
			}
			if (!isConsistencyEligibility(doc)) {
				continue;
			} 
			Map<Field, CrossFieldValidationType> fieldConsistencyMap = fetchConsistencyCheckFields(doc);
			
			crossValidateFields(doc, refernceConsistencyMap, fieldConsistencyMap, errorMessages);
			System.out.println("cross validation failed with doc : " +
					doc.getClass().getName() + " : \n" + errorMessages);
			errorMessages.clear();
		}
		crossValidateAmongNonReferenceDocuments(errorMessages, documentSet);	
	}

	private void crossValidateAmongNonReferenceDocuments(List<String> errorMessages, List<Document> documentSet)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		documentSet.remove(reference);
		if(documentSet.size() > 1) {
			reference = documentSet.get(0);
			crossValidate(errorMessages, documentSet);
		}
	}
	
	private void crossValidateFields(Document doc, Map<Field, CrossFieldValidationType> refernceConsistencyMap,
			Map<Field, CrossFieldValidationType> fieldConsistencyMap, List<String> errorMessages)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {
		for (Field field : fieldConsistencyMap.keySet()) {
			CrossFieldValidationType crossValidatationEnum = fieldConsistencyMap.get(field);
			if (refernceConsistencyMap.values().contains(crossValidatationEnum)) {
				Optional<Field> optionalField = refernceConsistencyMap.keySet().stream()
						.filter(key -> refernceConsistencyMap.get(key) == crossValidatationEnum).findFirst();
				if (optionalField.isPresent()) {
					Field referenceField = optionalField.get();
					if (!validateEqualFields(field, doc, referenceField, reference)) {
						errorMessages.add("Consistency check failed for the field : " + field.getName());
					}
				} else {
					errorMessages.add("Reference document doesn't contain the field : " + field.getName());
				}

			} else {
				errorMessages.add("In-consistence cross validation type  field : " + field.getName()
						+ " value2 : " + fieldConsistencyMap.get(field));
			}
		}

	}

	private boolean validateEqualFields(Field field, Document doc, Field referenceField, Document reference2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String getterMethod1 = ReflectionUtils.buildGetMethodName(referenceField.getName());
		Method method1 = reference.getClass().getMethod(getterMethod1, new Class[] {});
		String value1 = (String) method1.invoke(reference, new Object[] {});

		String getterMethod2 = ReflectionUtils.buildGetMethodName(field.getName());
		Method method2 = doc.getClass().getMethod(getterMethod2, new Class[] {});
		String value2 = (String) method2.invoke(doc, new Object[] {});

		return value1.equals(value2);
	}

	private Map<Field, CrossFieldValidationType> fetchConsistencyCheckFields(Document doc) {
		Map<Field, CrossFieldValidationType> map = new HashMap();
		Field[] fields = doc.getClass().getDeclaredFields();
		for (Field field : fields) {
			EqualFields equalFieldsAnnotation = (EqualFields) field.getAnnotation(EqualFields.class);
			if (!Objects.isNull(equalFieldsAnnotation)) {
				map.put(field, equalFieldsAnnotation.matchField());
			}
		}
		return map;
	}

	private boolean isConsistencyEligibility(Document doc) {
		boolean consistencyCheck = false;
		Class docClass = doc.getClass();
		Annotation docAnnotations = docClass.getAnnotation(CrossValidate.class);
		if (!Objects.isNull(docAnnotations)) {
			consistencyCheck = true;
		}
		return consistencyCheck;
	}
	
}
