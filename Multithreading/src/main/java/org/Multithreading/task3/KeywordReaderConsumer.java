package org.Multithreading.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

import org.Multithreading.task4.Trie;

public class KeywordReaderConsumer implements Consumer<File> {

	public static Trie trie = new Trie();
	
	@Override
	public void accept(File file) {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (int i = 0; i < lines.size(); i++) {
				String keyWord = lines.get(i).split("\t")[0];
				trie.insert(keyWord);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
