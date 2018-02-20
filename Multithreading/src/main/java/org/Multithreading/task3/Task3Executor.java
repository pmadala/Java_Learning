package org.Multithreading.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.Multithreading.task2.WikiRespsonseConsumer;
import org.Multithreading.task3.WordOccuranceCounter;
import org.Multithreading.task4.Trie;
import org.Multithreading.task4.TrieNode;

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
		for (File file : filesInFolder) {
			Consumer consumer = new KeywordReaderConsumer();
			consumer.accept(file);
		}		

		File locationFile = new File("output/task3/WordIndex.txt");
		if (!locationFile.exists())
			locationFile.createNewFile();
		
		StringBuilder  sb = new StringBuilder();
		Iterator iterator = new Trie.TrieIterator(KeywordReaderConsumer.trie);
		
		while (iterator.hasNext()) {
			Map.Entry<TrieNode,String> entry = (Map.Entry<TrieNode,String>) iterator.next();
			String keyword = entry.getValue();
			TrieNode node = entry.getKey();
			sb.append( keyword+ " ------> "+ node.getOccurance() +"\n");
		}
		Files.write(Paths.get(locationFile.toURI()), sb.toString().getBytes("utf-8"), 
				StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		
	}
}
