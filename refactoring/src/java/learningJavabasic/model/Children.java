package learningJavabasic.model;

/**
 * A class represents all the books in Children genre
 * @author priyambadam
 *
 */
public class Children extends ChildrenBook{

	public Children(String title) {
		super(title, Catagory.CHILDRENS);
	}
	
	@Override
	protected double getMultiplier() {
		return 2;
	}
	
	@Override
	protected double getThreshHold() {
		return 3;
	}
	
	@Override
	protected double getBaseAmount(double amount) {
		return amount += 1.5;
	}
}
