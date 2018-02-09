package learningJavabasic.generator;

import java.util.List;

import learningJavabasic.model.Rental;

/**
 * A class representing PDF statement generator
 * @author priyambadam
 *
 */
public class PDFStatementGenerator extends AbstractStatementGenerator{
	@Override
	public String generateStatement(List<Rental> rentals, String customerName ) {
		System.out.println("PDF statement generated.");
		return generateOutputStream(rentals, customerName);
	}

	@Override
	protected String getType() {
		return StatementGeneratorType.Email.toString();
	}

}
