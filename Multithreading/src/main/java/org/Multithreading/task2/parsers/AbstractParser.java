package org.Multithreading.task2.parsers;

/**
 * An abstract implementation of the parsers 
 * @author priyambadam
 *
 */
public abstract class AbstractParser implements Parser {
	String delimeter;
	String fileType;
	
	public AbstractParser(String fileType, String delimeter) {
		this.fileType = fileType;
		this.delimeter = delimeter;
	}
}
