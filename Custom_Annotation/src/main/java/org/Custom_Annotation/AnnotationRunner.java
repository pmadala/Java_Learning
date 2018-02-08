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
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;
import org.Custom_Annotation.model.Aadhar;
import org.Custom_Annotation.model.BankStatement;
import org.Custom_Annotation.model.Document;
import org.Custom_Annotation.model.PanCard;
import org.Custom_Annotation.model.Transaction;
import org.Custom_Annotation.validators.FieldValidator;
import org.Custom_Annotation.validators.FieldValidatorFactory;

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

		/*List<Document> documentSet = new ArrayList(3);
		documentSet.add(pan);
		documentSet.add(aadhar);
		documentSet.add(statement);
		validateCrossFieldAnnotations(errorMessages, pan, aadhar);*/

	}

	/*private static void validateCrossFieldAnnotations(List<String> errorMessages, Document pan, Document aadhar) {
		Annotation aadharAnnottaion = aadhar.getClass().getAnnotation(CrossValidate.class);
		Annotation panAnnottaion = pan.getClass().getAnnotation(CrossValidate.class);
		if (!Objects.isNull(aadharAnnottaion)) {
			Field[] fields = aadhar.getClass().getDeclaredFields();
			for (Field field : fields) {
				Annotation equalFieldAnnotation = field.getAnnotation(EqualFields.class);
				if (!Objects.isNull(equalFieldAnnotation)) {
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					Validator validator = factory.getValidator();
					Set<ConstraintViolation<Document>> constraintViolations =
				            validator.validate(aadhar);
					if (! ((Object) constraintViolations.iterator().next()).isValid(aadhar)) {
						errorMessages.add("Validation failed for fields : " + field.getName());
					}
				}
			}

		}

	}*/

	private static void validateFieldAnnotations(List<String> errorMessages, Document aadhar, Class aadharClass,
			Annotation aadharAnnottaion)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
		if (!Objects.isNull(aadharAnnottaion)) {
			Field[] fields = aadharClass.getDeclaredFields();// .getFields();
			for (Field field : fields) {
				CheckFor checkForAnnotation = (CheckFor) field.getAnnotation(CheckFor.class);

				if (!Objects.isNull(checkForAnnotation)) {
					ValidationType[] validationTypes = checkForAnnotation.type();

					for (ValidationType type : validationTypes) {
						Method method = getValueForField(field.getName(), aadharClass);
						Object object = method.invoke(aadhar);
						String value = object.toString();
						if (!validateByType(type, value)) {
							errorMessages.add("Validation failed for fields : " + field.getName());
						}
					}
				}
			}
		}
		System.out.println(errorMessages.toString());
	}

	private static boolean validateByType(ValidationType type, String valueForField) throws Exception {
		return ((FieldValidator) FieldValidatorFactory.getInstance(type)).validate(valueForField);
	}

	public static Method getValueForField(String fieldName, Class classInst) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder methodName = new StringBuilder("get");
		methodName.append(fieldName.substring(0, 1).toUpperCase());
		methodName.append(fieldName.substring(1, fieldName.length()));
		Method method = classInst.getMethod(methodName.toString(), new Class[] {});
		return method;
	}
}
