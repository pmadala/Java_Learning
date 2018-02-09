package learningJavabasic.model;

/**
 * A Factory class to generate book objects based on their type of genre
 * @author priyambadam
 *
 */
public class BookFactory {
	/**
	 * create a book instance with following details.
	 * @param catagory - Book genre
	 * @param title - name of teh book
	 * @return
	 */
	public static Book createBookInstance(Catagory catagory, String title) {
		Book book = null ;
		switch(catagory) {
			case FICTION:
				book = new Fiction(title);
				break;
			case NON_FICTION:
				book = new NonFiction(title);
				break;
			case CHILDRENS:
				book = new Children(title);
				break;
			default :
				System.out.println ("Invalid book catagory");
		}
		return book;
	}
}
