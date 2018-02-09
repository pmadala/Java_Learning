package learningJavabasic.generator;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import learningJavabasic.model.Rental;
import learningJavabasic.service.StatementService;

/**
 * Basic skeletal implementation of a statement generator 
 * @author priyambadam
 *
 */
public abstract class AbstractStatementGenerator implements StatementGenerator<Rental> {

	protected StatementService service;
	
	/**
	 * This should go to a different custom writer 
	 * 
	 * generate the statement with following details 
	 * @param rentals - list of rentals of a customer
	 * @param customerName
	 * @throws IOException 
	 */
	protected String generateOutputStream(List<Rental> rentals, String customerName) {

		if(Objects.isNull(service)) {
			service = new StatementService();
		}
		String content = service.fetchStatement(rentals, customerName);
		System.out.println(content);
		
		return content;		
	}

	protected String getType() {
		return "Basic";
	}
}
