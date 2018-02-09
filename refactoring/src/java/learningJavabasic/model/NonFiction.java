package learningJavabasic.model;

/**
 * A class represents all the books in Non-Fiction genre
 * @author priyambadam
 *
 */
public class NonFiction extends ChildrenBook{

	public NonFiction(String title) {
		super(title, Catagory.NON_FICTION);
	}
		
	@Override
	protected double getMultiplier() {
		return 3;
	}
}
