package learningJavabasic.generator;

import java.util.List;

/**
 * An interface for representing all the statement generators 
 * TODO : use generic over here 
 * 
 * Since this code is highly coupled for rental and use generic for implementing 
 * 
 * @author priyambadam
 *
 */
public interface StatementGenerator<E> {

	/**
	 * 
	 * Wrapper method to generate the customized statement 
	 *
	 * @param rentals
	 * @param customerName
	 * @return 
	 */
	public String generateStatement(List<E> rentals, String customerName); 
}
