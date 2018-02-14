package org.Custom_Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.EqualFields;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;
import org.Custom_Annotation.model.Aadhar;
import org.Custom_Annotation.model.BankStatement;
import org.Custom_Annotation.model.Document;
import org.Custom_Annotation.model.PanCard;
import org.Custom_Annotation.model.Transaction;
import org.Custom_Annotation.validators.EqualFieldValidator;
import org.Custom_Annotation.validators.FieldValidator;
import org.Custom_Annotation.validators.FieldValidatorFactory;
/**
 * A class invoke all the annotations both field based and cross field 
 * @author priyambadam
 *
 */
public class AnnotationRunner {

	public static void main(String args[]) throws Exception {

		List<String> errorMessages = new ArrayList();

		Document aadhar = new Aadhar("james", "M", "NY", Long.valueOf(12897), new Date(2017, 5, 3));
		Class classObj = aadhar.getClass();
		Annotation aadharAnnottaion = classObj.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, aadhar, classObj, aadharAnnottaion);

		Document pan = new PanCard("john", "Jack", "MNH787TY78", "NY", new Date(2017, 5, 3));
		Class panClass = pan.getClass();
		Annotation panAnnotations = panClass.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, pan, panClass, panAnnotations);

		Transaction transaction = new Transaction("1", "first transaction", 9.8, new Date(2017, 5, 3));
		classObj = transaction.getClass();
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Document statement = new BankStatement("575897no08", "harry", transactions, "NY", "69474993", "abc@xyz.com");
		classObj = statement.getClass();
		Class statementClass = statement.getClass();
		Annotation statementAnnotations = statementClass.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, statement, statementClass, statementAnnotations);

		if (validateEqualFields(pan, aadhar)) {
			errorMessages.add("consistant check failed for pan and aadhar");
		}
		if (validateEqualFields(pan, statement)) {
			errorMessages.add("consistant check failed for pan and statement");
		}
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
					ValidationType[] validationTypes = checkForAnnotation.type();

					for (ValidationType type : validationTypes) {
						Method method = getValueForField(field.getName(), classObj);
						Object object = method.invoke(doc);
						String value = Objects.isNull(object)? "" : object.toString();
						if (!validateByType(type, value,field, doc)) {
							errorMessages.add("Validation failed for fields : " + field.getName());
						}
					}
				}
			}
		}
		System.out.println(errorMessages.toString());
	}

	private static boolean validateByType(ValidationType type, String valueForField, Field field, Document doc) throws Exception {
		FieldValidator validator = ((FieldValidator) FieldValidatorFactory.getInstance(type));
		if (validator.isEqualFieldValidator()) {
			EqualFieldValidator equalFieldValidator = (EqualFieldValidator) validator;
			EqualFields equalFieldAnnotation = field.getAnnotation(EqualFields.class);
			if (!Objects.isNull(equalFieldAnnotation)) {
				equalFieldValidator.initialise(valueForField, equalFieldAnnotation.matchClass(), equalFieldAnnotation.matchField());			
				return equalFieldValidator.validate(valueForField);
			}
		}
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
}
