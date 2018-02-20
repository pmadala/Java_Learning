package org.Multithreading.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class representing the recursive splitter task for file writing 
 * @author priyambadam
 *
 */
public class FileWriterRecursiveAction extends RecursiveAction {

	private static final int threshold = 1000;
	private String outputDir;
	private List<String> fileLines;
	private int noOfLines;
	private static final AtomicInteger atomicInt = new AtomicInteger();

	public FileWriterRecursiveAction(String outputDir, List<String> fileLines) {
		super();
		this.outputDir = outputDir;
		this.fileLines = fileLines;
		this.noOfLines = fileLines.size();
	}

	@Override
	protected void compute() {

		if (noOfLines <= threshold) {
			writeToFile(fileLines);
		} else {
			RecursiveAction firstFileWriter = new FileWriterRecursiveAction(outputDir, fileLines.subList(0, threshold));
			RecursiveAction secondFileWriter = new FileWriterRecursiveAction(outputDir,
					fileLines.subList(threshold, fileLines.size()));
			invokeAll(firstFileWriter, secondFileWriter);
		}

	}

	private void writeToFile(List<String> lines) {

		Path path = Paths.get(outputDir + atomicInt.getAndIncrement() + ".txt");
		try {
			for (String line : lines) {
				Files.write(path, line.getBytes());

			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
