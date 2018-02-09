package learningJavabasic.generator;

import java.util.List;

import learningJavabasic.model.Rental;

/**
 * A class representing Email statement generator
 * @author priyambadam
 *
 */
public class EMailStatementGenerator extends AbstractStatementGenerator{

	@Override
	public String generateStatement(List<Rental> rentals, String customerName) {
		System.out.println("Email statement generated.");
		return generateOutputStream(rentals, customerName);
	}
	
	@Override
	protected String getType() {
		return StatementGeneratorType.PDF.toString();
	}
}
