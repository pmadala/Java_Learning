/**
 * 
 */
package learningJavabasic.model;

import java.io.Serializable;

/**
 * Represent a customer renting a book.
 * 
 * @author priyambadam
 *
 */
public class Rental implements Serializable {

	private static final long serialVersionUID = 1256869448913370768L;

	private Book book;

	private int daysRented;

	public Rental(Book book1, int daysRented) {
		super();
		this.book = book1;
		this.daysRented = daysRented;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}
	
	public double fetchCharges(double amount) {
		book.fetchCharges(amount, daysRented);
	}
	
	public int fetchPoints(int frequentRenterPoints) {
		book.fetchPoints(frequentRenterPoints, this);
	}
}
