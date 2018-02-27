package org.Custom_Annotation;

import java.util.ArrayList;
import java.util.List;

import org.Custom_Annotation.model.Aadhar;
import org.Custom_Annotation.model.Document;
import org.Custom_Annotation.model.DocumentCollectionGenerator;
import org.Custom_Annotation.validators.CrossValidator;
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
		
		CrossValidator.INSTANCE.setReferenceDoc(Aadhar.class, documentSet);
		CrossValidator.INSTANCE.crossValidate(errorMessages, documentSet);
	}

	

}
