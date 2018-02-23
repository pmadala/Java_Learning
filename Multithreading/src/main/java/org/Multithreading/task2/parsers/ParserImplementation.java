package org.Multithreading.task2.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A parser implementation for the flat files based on a given delimiter
 * @author priyambadam
 *
 */
public class ParserImplementation extends AbstractParser{
	
	public ParserImplementation(String fileType, String delimeter) {
		super(fileType, delimeter);
	}

	/**
	 * Retrieve the file contents in a path given . 
	 * Parse the response for the content of each line in the file and return 
	 */
	public List<List<String>> getParser(Path path) throws IOException {
		List<List<String>> allFileContent = new ArrayList<>();
		
		List<String> lines = Files.readAllLines(path);
		for (String line : lines) {
			List<String> allLineContent= Arrays.asList(line.split(delimeter));
			allFileContent.add(allLineContent);
		}
		
		return allFileContent;
	}
}
