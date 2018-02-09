package learningJavabasic.model;

/**
 * An interface for representing all the books 
 * @author priyambadam
 *
 */
public interface Book {

	//defines logic for charge calculation associated with a book
	public double fetchCharges(double amount, int daysRented) ;
	
	//defines logic for points calculation associated with a book
	public int fetchPoints(int frequentRenterPoints, Rental rental);
	
	//returns the title of the book
	public String getTitle();
}
