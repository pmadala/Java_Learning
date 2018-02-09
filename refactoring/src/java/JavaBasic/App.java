package JavaBasic;

import learningJavabasic.generator.StatementGeneratorType;
import learningJavabasic.model.Book;
import learningJavabasic.model.BookFactory;
import learningJavabasic.model.Catagory;
import learningJavabasic.model.Customer;
import learningJavabasic.model.Rental;
import learningJavabasic.statementwritter.StatementWriter;
import learningJavabasic.statementwritter.StatementWriterFactory;
import learningJavabasic.statementwritter.StatementWriterType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Book book1 = BookFactory.createBookInstance(Catagory.FICTION, "Harry Potter");
        Book book2 = BookFactory.createBookInstance(Catagory.NON_FICTION, "Monk who sold his Farrari");
        Book book3 = BookFactory.createBookInstance(Catagory.CHILDRENS, "Lion King");
        Book book4 = BookFactory.createBookInstance(Catagory.FICTION, "Song of Ice and Firer");

        Customer customer1 = new Customer("Larry");
        customer1.initializeStatementGenerator(StatementGeneratorType.PDF);
        Customer customer2 = new Customer("John");
        customer2.initializeStatementGenerator(StatementGeneratorType.Email);;
       
        Rental rental1 = new Rental(book1, 26);
        Rental rental2 = new Rental(book2, 10);
        Rental rental3 = new Rental(book3, 8);
        Rental rental4 = new Rental(book4, 20);
        Rental rental5 = new Rental(book1, 25);
       
        customer1.addRental(rental1);
        customer1.addRental(rental3);
        String content = customer1.fetchStatement();
        StatementWriter writter = StatementWriterFactory.getInstance(content, "pdf", StatementWriterType.local);
        writter.writeStatements();
        
        System.out.println();
        System.out.println("------------------");
        System.out.println();
        
        customer2.addRental(rental2);
        customer2.addRental(rental5);
        customer2.addRental(rental4);
        content = customer2.fetchStatement();
        writter = StatementWriterFactory.getInstance(content, "txt", StatementWriterType.local);
        writter.writeStatements();
    }

	
}
