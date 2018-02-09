package learningJavabasic.statementwritter;

/**
 * A skeletal representation of the writer
 * @author priyambadam
 *
 */
public abstract class AbstractStatementWriter implements StatementWriter {
	protected String content;
	protected String fileType;
	
	public AbstractStatementWriter(String content, String fileType) {
		this.content = content;
		this.fileType = fileType;
	}
	
}
