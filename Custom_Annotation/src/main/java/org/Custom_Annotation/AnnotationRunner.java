package org.Custom_Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.Custom_Annotation.annotations.AadharValidator;
import org.Custom_Annotation.annotations.BankStatementValidator;
import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.PanValidate;
import org.Custom_Annotation.annotations.TransactionValidator;
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
		Class aadharClass = aadhar.getClass();
		Annotation aadharAnnottaion = aadharClass.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, aadhar, aadharClass, aadharAnnottaion);
		
		Document pan = new PanCard("john","Jack","MNH787TY78", "NY", new Date(2017, 5, 3));
		Class panClass = pan.getClass();
		Annotation panAnnotations = panClass.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, pan, panClass, panAnnotations);
		
		Transaction transaction = new Transaction("1","first transaction",9.8, new Date(2017, 5, 3));aadharClass=transaction.getClass();
		List<Transaction> transactions = new ArrayList<>();transactions.add(transaction);
		Document statement = new BankStatement("575897no08","harry",transactions, "NY", "69474993","abc@xyz.com");aadharClass=statement.getClass();
		Class statementClass = statement.getClass();
		Annotation statementAnnotations = statementClass.getAnnotation(Validate.class);
		validateFieldAnnotations(errorMessages, statement, statementClass, statementAnnotations);
		
	}

	private static void validateFieldAnnotations(List<String> errorMessages, Document aadhar, Class aadharClass,
			Annotation aadharAnnottaion)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
		if (!Objects.isNull(aadharAnnottaion)) {
			Field[] fields = aadharClass.getDeclaredFields();//.getFields();
			for (Field field : fields) {
				CheckFor checkForAnnotation = (CheckFor) field.getAnnotation(CheckFor.class);

				if (!Objects.isNull(checkForAnnotation)) {
					ValidationType[] validationTypes = checkForAnnotation.type();

					for (ValidationType type : validationTypes) {
						Method method = getValueForField(field.getName(), aadharClass);
						Object object = method.invoke(aadhar);
						String value = object.toString();
						if (!validateByType(type, value)) {
							errorMessages.add("Validation failed for fields : " +  field.getName());
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

	/*AadharValidator aAnnotation = (AadharValidator) aadharAnnottaion;System.out.println("Aadhar Name: "+aAnnotation.fullname());System.out.println("Aadhar Number: "+aAnnotation.aadharNumber());System.out.println("Aadhar Gender: "+aAnnotation.gender());System.out.println("Aadhar Address: "+aAnnotation.address());System.out.println("Aadhar dob: "+aAnnotation.dob());

	System.out.println();

	PanCard pan = new PanCard("john","Jack","MNH787", "NY", new Date(2017, 5, 3));
	aadharClass=pan.getClass();
	Annotation panAnnotation = aadharClass.getAnnotation(PanValidate.class);
	PanValidate pAnnotation = (PanValidate) panAnnotation;System.out.println("PAN Name: "+pAnnotation.fullname());System.out.println("PAN Number: "+pAnnotation.panNumber());System.out.println("PAN Father Name: "+pAnnotation.fatherName());System.out.println("PAN issuedby: "+pAnnotation.issuedBy());System.out.println("PAN dob: "+pAnnotation.dob());

	System.out.println();

	Transaction transaction = new Transaction("1","first transaction",9.8, new Date(2017, 5, 3));aadharClass=transaction.getClass();
	Annotation transactionAnnotation = aadharClass.getAnnotation(TransactionValidator.class);
	TransactionValidator tAnnotation = (TransactionValidator) transactionAnnotation;System.out.println("Transaction id: "+tAnnotation.id());System.out.println("Transaction amount: "+tAnnotation.amount());System.out.println("Transaction description: "+tAnnotation.description());System.out.println("Transaction transaction time: "+tAnnotation.transactionTime());

	System.out.println();

	List<Transaction> transactions = new ArrayList<>();transactions.add(transaction);
	BankStatement statement = new BankStatement("575897no08","harry",transactions, "NY", "69474993","abc@xyz.com");aadharClass=statement.getClass();
	Annotation statementAnnotation = aadharClass.getAnnotation(BankStatementValidator.class);
	BankStatementValidator sAnnotation = (BankStatementValidator) statementAnnotation;System.out.println("Bank statement number: "+sAnnotation.accountNumber());System.out.println("Bank statement name: "+sAnnotation.customerName());System.out.println("Bank statement transaction: "+sAnnotation.transactions());System.out.println("Bank statement address: "+sAnnotation.address());System.out.println("Bank statement Mobile: "+sAnnotation.mobileNumber());System.out.println("Bank statement email: "+sAnnotation.email());

	}*/

	public static Method getValueForField(String fieldName, Class classInst) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder methodName = new StringBuilder("get");
		methodName.append(fieldName.substring(0, 1).toUpperCase());
		methodName.append(fieldName.substring(1, fieldName.length()));
		Method method = classInst.getMethod(methodName.toString(),  new Class[] {});
		return method;
	}
}
