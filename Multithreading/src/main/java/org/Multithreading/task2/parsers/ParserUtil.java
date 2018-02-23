package org.Multithreading.task2.parsers;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
/**
 * A class with validates and parse files and return its content to the caller 
 * @author priyambadam
 *
 */
public enum ParserUtil {
	INSTANCE;

	public List parseFile(Path path, String fileType, String delimeter) throws Exception {
		if (FileValidator.valiadate(path, fileType)) {
			Parser parser = new ParserImplementation(fileType, delimeter);
			return parser.getParser(path);
		}
		return Collections.EMPTY_LIST;
	}
}
