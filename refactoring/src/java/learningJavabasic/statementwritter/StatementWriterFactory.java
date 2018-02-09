package learningJavabasic.statementwritter;

/**
 * Factory implementation of writer object
 * @author priyambadam
 *
 */
public class StatementWriterFactory {
	public static StatementWriter getInstance(String content, String fileType, StatementWriterType writerType ) {
		StatementWriter service = null;
		switch(writerType) {
			case local:
				service = new LocalStatementWriter(content,fileType);
				break;
			case SMTP: 
				break;
			case AWS:
				break;
		}
		return service;
	}
}
