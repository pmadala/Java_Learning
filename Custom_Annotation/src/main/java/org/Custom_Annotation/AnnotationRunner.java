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
import org.Custom_Annotation.model.DocumentCollectionGenerator;
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
		List<Document> documentSet = DocumentCollectionGenerator.INSTANCE.generateDocumentSet();
		
		for(Document doc : documentSet) {
			AnnotationRunnerHelper.INSTANCE.validateFieldAnnotations(errorMessages,doc);
			System.out.println("Validation Fail with doc : " +
							doc.getClass().getName() + " : " + errorMessages);
			errorMessages.clear();
		}
		
		/*if (AnnotationRunnerHelper.INSTANCE.validateEqualFields(pan, aadhar)) {
			errorMessages.add("consistant check failed for pan and aadhar");
		}
		if (validateEqualFields(pan, statement)) {
			errorMessages.add("consistant check failed for pan and statement");
		}*/
	}

	

}
