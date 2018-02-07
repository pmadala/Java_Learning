package org.Multithreading.task2.Threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A thread responsible for reading the company keywords from file
 * @author priyambadam
 *
 */
public class CompaniesThread implements Runnable {

	@Override
	public void run() {
		System.out.println("Started CompaniesThread ");
		File file = new File ("../common/data/Multithreading_Task_2_fortune1000companies.txt");
		try {
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (int i=0;i<lines.size(); i++) {
				String[] records = lines.get(i).split("\n");
				List<String> keywords = new ArrayList();
				for(String record : records) {
					keywords.add(record.split("\t")[1]);
				}
				
				List<String> keyWordList = keywords.stream().collect(Collectors.toList());
				KeyWordRepository.addAllKeyword(keyWordList);
			}
		} catch (IOException e) {
			System.out.println("Not able to raed data from file ." + e.getMessage());
			e.printStackTrace();
		}			
		System.out.println("Ended CompaniesThread ");
	}

}
