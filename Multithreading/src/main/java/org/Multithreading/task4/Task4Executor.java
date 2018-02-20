package org.Multithreading.task4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.Multithreading.task3.KeywordReaderConsumer;
import org.apache.commons.lang3.StringUtils;

/**
 * A class to implement auto suggest on a list of keywords 
 * 
 * @author priyambadam
 *
 */
public class Task4Executor {
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

		Scanner sc=new Scanner(System.in);  
		String prefix = sc.next();
		KeywordReaderConsumer.trie.autoComplete(prefix).stream().forEach(System.out::println);
	}
}
