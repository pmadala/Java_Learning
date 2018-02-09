/**
 * 
 */
package learningJavabasic.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic skeletal implementation of a Book 
 * @author priyambadam
 *
 */
public abstract class ChildrenBook implements Serializable, Book {

	private static final long serialVersionUID = -7348792584072115788L;

	private Date releaseDate;

	private long id;
	private String title;
	private Catagory bookCategory;

	//TODO : use builder for it 
	public ChildrenBook(final String title, final Catagory bookCategory, final Date releaseDate) {
		super();
		this.title = title;
		this.bookCategory = bookCategory;
		this.releaseDate = releaseDate;
	}

	public ChildrenBook(final String title, final Catagory bookCategory) {
		super();
		this.title = title;
		this.bookCategory = bookCategory;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Catagory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(Catagory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	protected double getMultiplier() {
		return 1;
	}
	
	protected double getThreshHold() {
		return 0;
	}
	
	protected double getBaseAmount(double amount) {
		return amount;
	}
	
	public double fetchCharges(double amount, int daysRented) {
		amount = getBaseAmount(amount);
		if (daysRented > getThreshHold())
			amount += (daysRented - getThreshHold()) * getMultiplier();
		return amount;
	}
	
	public int fetchPoints(int frequentRenterPoints, Rental rental) {
		frequentRenterPoints++;;
		return frequentRenterPoints;
	}
}
