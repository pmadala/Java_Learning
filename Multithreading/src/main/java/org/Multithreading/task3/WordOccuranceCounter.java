package org.Multithreading.task3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A callable task to to find the occurance of each word in a file given 
 * 
 * @author priyambadam
 *
 */
public class WordOccuranceCounter implements Callable<Map> {

	private File file;

	public WordOccuranceCounter(File file) {
		this.file = file;
	}

	@Override
	public Map call() throws Exception {
		Map<String, Long> map = new HashMap<>();
		Path textFilePath = Paths.get(file.getAbsolutePath());
		try {
			List<String> lines = Files.readAllLines(textFilePath, Charset.defaultCharset());
			for(String line :lines) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (map.containsKey(word)) {
						map.put(word, map.get(word)+1);
					}else {
						map.put(word, new Long(1));
					}
				}
			}
			return  map;
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return Collections.EMPTY_MAP;
	}

}
