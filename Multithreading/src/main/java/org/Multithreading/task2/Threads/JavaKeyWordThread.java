package org.Multithreading.task2.Threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * A thread responsible for reading the Java keywords from file
 * @author priyambadam
 *
 */
public class JavaKeyWordThread implements Runnable {

	@Override
	public void run() {
		System.out.println("Started JavaKeyWordThread ");
		File file = new File ("../common/data/Multithreading_Task_2_java Keywords.txt");
		try {
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (int i=0;i<lines.size(); i++) {
				String[] keyWords = lines.get(i).split(",");
				List<String> keyWordList = Arrays.asList(keyWords).stream().filter(keyword-> StringUtils.isNotBlank(keyword)).collect(Collectors.toList());
				KeyWordRepository.addAllKeyword(keyWordList);
			}
		} catch (IOException e) {
			System.out.println("Not able to raed data from file ." + e.getMessage());
			e.printStackTrace();
		}	
		System.out.println("Ended JavaKeyWordThread ");
	}

}
