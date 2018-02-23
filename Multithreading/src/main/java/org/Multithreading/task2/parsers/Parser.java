package org.Multithreading.task2.parsers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * An interface representing the type of all parsers 
 * @author priyambadam
 *
 */
public interface Parser {
	public List<List<String>> getParser(Path path) throws IOException ;
}
