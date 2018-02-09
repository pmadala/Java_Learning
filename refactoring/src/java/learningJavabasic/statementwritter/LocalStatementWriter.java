package learningJavabasic.statementwritter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class representing writer for local disk
 * @author priyambadam
 *
 */
public class LocalStatementWriter extends AbstractStatementWriter{

	public LocalStatementWriter(String content, String fileType) {
		super(content, fileType);
	}

	@Override
	public void writeStatements() {
		Path path = Paths.get("statementFile"+ fileType+".txt");
		try {
			Files.write(path, content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
}
