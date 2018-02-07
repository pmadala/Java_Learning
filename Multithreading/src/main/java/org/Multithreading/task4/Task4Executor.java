package org.Multithreading.task4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * A class to implement auto suggest on a list of keywords 
 * 
 * @author priyambadam
 *
 */
public class Task4Executor {
	public static void main(String args[]) {
		File file = new File("output/task3/WordIndex.txt");
		Trie trie = new Trie();

		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (int i = 0; i < lines.size(); i++) {
				String keyWords = lines.get(i).split("\t")[0];
				trie.insert(keyWords);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner sc=new Scanner(System.in);  
		String prefix = sc.next();
		trie.autoComplete(prefix).stream().forEach(System.out::println);
	}
}
