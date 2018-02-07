package org.Multithreading.task2.Threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A thread responsible for reading the programing language keywords from file
 * @author priyambadam
 *
 */
public class ProgrammingLanguageThread implements Runnable{

	@Override
	public void run() {
		System.out.println("Started ProgrammingLanguageThread ");
		File file = new File ("../common/data/Multithreading_Task2_ProgrammingLanguages.txt");
		try {
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (int i=0;i<lines.size(); i++) {
				String keyWords = lines.get(i);
				List<String> keyWordList = Arrays.asList(keyWords).stream().collect(Collectors.toList());
				KeyWordRepository.addAllKeyword(keyWordList);
			}
		} catch (IOException e) {
			System.out.println("Not able to raed data from file ." + e.getMessage());
			e.printStackTrace();
		}	
		System.out.println("Ended ProgrammingLanguageThread ");
	}

}
