package learningJavabasic.model;

/**
 * A class represents all the books in Fiction genre
 * @author priyambadam
 *
 */
public class Fiction extends ChildrenBook {

	public Fiction(String title) {
		super(title, Catagory.FICTION);
	}
	
	@Override
	protected double getMultiplier() {
		return 1.5;
	}
	
	@Override
	protected double getThreshHold() {
		return 2;
	}
	
	@Override
	protected double getBaseAmount(double amount) {
		return amount += 2;
	}
	
	@Override
	public int fetchPoints(int frequentRenterPoints, Rental rental) {
		frequentRenterPoints = super.fetchPoints(frequentRenterPoints, rental);
		if (rental.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}
}
