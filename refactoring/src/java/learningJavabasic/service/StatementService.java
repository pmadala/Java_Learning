package learningJavabasic.service;

import java.util.Iterator;
import java.util.List;

import learningJavabasic.model.Rental;

/**
 * A service class for statement operations 
 * @author priyambadam
 *
 */
public class StatementService {
	
	private double fetchCharges(double amount, Rental rental) {
		return rental.fetchCharges(amount);
	}
	
	private int fetchPoints(int frequentRenterPoints, Rental rental) {
		return rental.fetchPoints(frequentRenterPoints);
	}
	
	/**
	 * Creates statements associated witha customer
	 * @param rentals
	 * @param customerName
	 * @return
	 */
	public String fetchStatement(List<Rental> rentals, String customerName) {

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentalsItr = rentals.listIterator();
		StringBuilder result = new StringBuilder("Rental Record for " + customerName + "\n");
		
		while (rentalsItr.hasNext()) {
			double thisAmount = 0;
			Rental rental = (Rental) rentalsItr.next();

			thisAmount = fetchCharges(thisAmount, rental);
			frequentRenterPoints = fetchPoints(frequentRenterPoints, rental);

			result.append("\t").append(rental.getBook().getTitle()).append("\t").append(String.valueOf(thisAmount))
					.append("\n");
			totalAmount += thisAmount;
		}
		
		result.append("Amount owed is ").append(String.valueOf(totalAmount)).append("\n");
		result.append("You earned ").append(String.valueOf(frequentRenterPoints)).append(" frequent renter points");
		
		
		return result.toString();
	}
	
}
