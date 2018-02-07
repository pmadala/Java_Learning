package org.Multithreading.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.Multithreading.IConstants;

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

		WorkerThread.setLines(readFile());
		if (WorkerThread.getLines().isEmpty()) {
			throw new Exception("Read file is not successful.");
		}
		ExecutorService executor = Executors.newFixedThreadPool(IConstants.MAX_NUMBER_OF_WORKER_THREADS);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new WorkerThread("Thread-" + (i + 1));
			executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

	/**
	 * read the entire input file and returns all the list of lines from file  
	 */
	private static List<String> readFile() {
		File file = new File("../common/data/Multithreading_Task1_Books.csv");
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