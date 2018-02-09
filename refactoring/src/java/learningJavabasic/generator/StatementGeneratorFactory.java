package learningJavabasic.generator;

/**
 * Initializes the kind of statement generator customer wants to create 
 * @author priyambadam
 *
 */
public class StatementGeneratorFactory {

	/**
	 * create a statement generator instance with given type.
	 * @param generatorType
	 * @return
	 */
	public static StatementGenerator createStatementGenerator(StatementGeneratorType generatorType) {
		StatementGenerator service = null;
		switch(generatorType) {
			case PDF:
				service = new PDFStatementGenerator();
				break;
			case Email: 
				service = new EMailStatementGenerator();
				break;
		}
		return service;
	}
}
