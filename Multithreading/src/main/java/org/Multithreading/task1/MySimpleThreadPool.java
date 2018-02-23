package org.Multithreading.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.Multithreading.task2.TaskExecutorHelper;

/**
 * A class which creates thread pool for creating and executing threads for
 * splitting a large file into multiple files
 * 
 * TODO: needs to implement fork and join here 
 * 
 * @author priyambadam
 *
 */
public class MySimpleThreadPool {

	public static void main(String args[]) throws Exception {
		TaskExecutorHelper helper = new TaskExecutorHelper();
		final String fileLocation = helper.getCSVfielLocationFromproperties();
		
		//Using fork and join 
		String outputdir = "output/task1/";
		List<String> fileLines = readFile(fileLocation);
		FileWriterRecursiveAction writerAction = new FileWriterRecursiveAction(outputdir, fileLines);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.invoke(writerAction);
		System.out.println("Finished all threads");
	}

	/**
	 * read the entire input file and returns all the list of lines from file  
	 * @param fileLocation 
	 */
	private static List<String> readFile(String fileLocation) {		
		File file = new File(fileLocation);
		try {
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			return lines;
		} catch (IOException e) {
			System.out.println("Not able to read data from file ." + e.getMessage());
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
}