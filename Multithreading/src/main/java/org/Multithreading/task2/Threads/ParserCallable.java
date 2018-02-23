package org.Multithreading.task2.Threads;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.Multithreading.task2.parsers.ParserUtil;

/**
 * A thread responsible for reading the from file and storing keywords in a repo
 * 
 * @author priyambadam
 *
 */
public class ParserCallable implements Callable<List> {
	private String key;
	private String value ;

	public ParserCallable(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public List call() throws Exception {
		System.out.println("Started Parsing Thread ");
		List<String> keyWords = new ArrayList<>();

		Path path = Paths.get(key);

		String fileType = value.split("\\|")[0];
		String delimiter = value.split("\\|")[1];
		int elementIndex = Integer.parseInt(value.split("\\|")[2]);
		List<List<String>> allFileContent = ParserUtil.INSTANCE.parseFile(path, fileType, delimiter);
		for (List<String> lineContent : allFileContent) {
			keyWords.add(lineContent.stream().skip(elementIndex - 1).findFirst().get());
		}
		
		System.out.println("Ended Parsing Thread ");
		return keyWords;
	}

}
