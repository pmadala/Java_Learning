package org.Multithreading.task2.Threads;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class which holds all the key values read from the threads  
 * @author priyambadam
 *
 */
public class KeyWordRepository {
	private static Set<String> keyWords = Collections.synchronizedSet(new HashSet<>());
	
	public static void addKeyword(String keyword) {
		keyWords.add(keyword);
	}
	
	public synchronized static void addAllKeyword(List<String> list) {
		keyWords.addAll(list);
	}


	public static Set<String> getKeyWords() {
		return keyWords;
	}	
}
