/**
 * 
 */
package learningJavabasic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import learningJavabasic.generator.StatementGenerator;
import learningJavabasic.generator.StatementGeneratorFactory;
import learningJavabasic.generator.StatementGeneratorType;


/**
 * Customer class that denotes customer details as well as customer related
 * operations.
 * 
 * @author priyambadam
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 851426779607326255L;

	private long id;
	private String name;
	private static StatementGenerator generator;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		super();
		this.name = name;
	}

	/**
	 * Generates the statement for a customer based on all its rentals 
	 */
	public String fetchStatement() {
		return generator.generateStatement(rentals, getName());
	}
	
	/**
	 * Initializes the kind of statement generator customer wants to create 
	 * @param generatorType
	 */
	public void initializeStatementGenerator(StatementGeneratorType generatorType) {
		generator = StatementGeneratorFactory.createStatementGenerator(generatorType);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void addRental(final Rental rental) {
		getRentals().add(rental);
	}

}
