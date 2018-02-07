package org.Multithreading.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.Multithreading.task3.WordOccuranceCounter;

/**
 * A class responsible to visit through each file in a folder and find the count of each unique word 
 * 
 * Also additionally it is creating the indexing on the files per word for searching .
 * 
 * @author priyambadam
 *
 */
public class Task3Executor {
	public static void main(String args[]) throws Exception {
		List<File> filesInFolder = null;
		try {
			filesInFolder = Files.walk(Paths.get("output/task3_input/")).filter(Files::isRegularFile).map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (filesInFolder.isEmpty())
			throw new Exception("No files available in folder");

		Map<String, List<String>> fileLocation = new HashMap<>();
		Map<String, Long> wordCount = new HashMap<>();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (File file : filesInFolder) {
			Future future = service.submit(new WordOccuranceCounter(file));
			synchronized (wordCount) {
				if (future.get() instanceof Map) {
					Map<String, Long> wordCountInFile = (Map<String, Long>) future.get();
					for (Map.Entry<String, Long> entry : wordCountInFile.entrySet()) {
						List loc = fileLocation.get(entry.getKey()) == null ? new ArrayList<>(): fileLocation.get(entry.getKey());
						if (!loc.contains(file.getAbsolutePath())) {
							loc.add(file.getAbsolutePath());
							fileLocation.put(entry.getKey(), loc);
						}
						if (wordCount.containsKey(entry.getKey())) {
							wordCount.put(entry.getKey(), entry.getValue() + wordCount.get(entry.getKey()));
						} else {
							wordCount.put(entry.getKey(), entry.getValue());
						}
					}
				}
			}
		}
		for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
			System.out.println(entry.getKey() + " ------> " + entry.getValue());

		}
		File locationFile = new File("output/task3/WordIndex.txt");
		if (!locationFile.exists())
			locationFile.createNewFile();
		
		StringBuilder  sb = new StringBuilder();
		for (Map.Entry<String, List<String>> entry : fileLocation.entrySet()) {
			String fileEntry = entry.getKey()+"\t";
			fileEntry += entry.getValue().stream().map(Object::toString).collect(Collectors.joining(","));
			sb.append(fileEntry + "\n");
			Files.write(Paths.get(locationFile.toURI()), sb.toString().getBytes("utf-8"), 
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			sb.setLength(0);;
		}

		// kJoinPool forkJoinPool = new ForkJoinPool(3);
		// RecursiveWikiRequestResponse task = new RecursiveWikiRequestResponse();
		// forkJoinPool.invoke(task);
	}
}
