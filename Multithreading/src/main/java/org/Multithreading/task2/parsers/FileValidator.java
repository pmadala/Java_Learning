package org.Multithreading.task2.parsers;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class for verification of the input file according to the standards pre-defined 
 * @author priyambadam
 *
 */
public class FileValidator {

	public static boolean valiadate(Path path, String fileType) throws Exception {
		String absolutePath = path.toAbsolutePath().toString();
		if (Files.notExists(path)) {
			throw new Exception("File doesnt exist at path : " + absolutePath);
		}
		
		if(!(absolutePath.contains(fileType))) {
			throw new Exception("File type is not compatiable with the existing file");
		}
		return true;
	}

}
