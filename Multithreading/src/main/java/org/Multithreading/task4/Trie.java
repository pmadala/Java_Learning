package org.Multithreading.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * An implementation of trie datastructure 
 * @author priyambadam
 *
 */
public class Trie {
	private int minimumBreak = Integer.MAX_VALUE;
	private final TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	/**
	 * Utility method to add words into a trie data structure
	 * Iterative approach used
	 * 
	 */
	public void insert(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if (node == null) {
				node = new TrieNode();
				current.children.put(ch, node);
			}
			current = node;
		}
		current.endOfWord = true;
	}

	/**
	 * Utility method to add words into a trie data structure
	 * Recursive approach used 
	 */
	public void insertRecursively(String word) {
		insertRecursively(word, 0, root);
	}

	private void insertRecursively(String word, int index, TrieNode current) {
		if (index == word.length()) {
			current.endOfWord = true;
			return;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if (node == null) {
			node = new TrieNode();
			current.children.put(ch, node);
		}
		insertRecursively(word, index + 1, node);
	}

	/**
	 * check for a perticular word existing in a trie data structure 
	 * The input needs to be a entire word 
	 */
	public boolean hasWord(String word) {
		TrieNode node = searchNode(word);
		if (node != null && node.endOfWord == true)
			return true;
		else
			return false;
	}

	/**
	 * search for a word which starts with a prefix 
	 */
	public boolean startsWith(String prefix) {
		TrieNode node = searchNode(prefix);
		return node != null;
	}

	private TrieNode searchNode(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if (node == null) {
				return null;
			}
			current = node;
		}
		return current;
	}

	/**
	 * Delete a word from trie data structure 
	 */
	public void delete(String word) {
		delete(word, 0, root);
	}

	private boolean delete(String word, int index, TrieNode trieNode) {
		if (index == word.length()) {
			if (!trieNode.endOfWord)
				return false;
			trieNode.endOfWord = false;
			return trieNode.children.size() == 0;
		}
		char ch = word.charAt(index);
		TrieNode node = trieNode.children.get(ch);
		if (node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(word, index + 1, node);
		if (shouldDeleteCurrentNode) {
			node.children.remove(ch);
			return node.children.size() == 0;
		}
		return false;
	}

	/**
	 * Get the word count in trie data structure 
	 */
	public int wordCount() {
		return wordCount(root);
	}

	private int wordCount(TrieNode node) {
		int result = 0;
		if (node.endOfWord == true)
			result++;
		for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
			result += wordCount(entry.getValue());
		}
		return result;
	}

	/**
	 * How can we improve this? The number of matches might just be too large so we
	 * have to be selective while displaying them. We can restrict ourselves to
	 * display only the relevant results. By relevant, we can consider the past
	 * search history and show only the most searched matching strings as relevant
	 * results. Store another value for the each node where isleaf=True which
	 * contains the number of hits for that query search. For example if “al” is
	 * searched 10 times, then we store this 10 at the last node for “al”. Now when
	 * we want to show the recommendations, we display the top k matches with the
	 * highest hits.
	 *
	 * @param prefix
	 * @return
	 */
	public List<String> autoComplete(String prefix) {
		TrieNode current = root;
		for (char ch : prefix.toCharArray()) {
			if (!current.children.containsKey(ch))
				return Collections.emptyList();
			current = current.children.get(ch);
		}
		return getAllWords(current, prefix);
	}

	private List<String> getAllWords(TrieNode node, String prefix) {
		List<String> words = new ArrayList<>();
		if (node.endOfWord)
			words.add(prefix);
		for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
			words.addAll(getAllWords(entry.getValue(), prefix + entry.getKey()));
		}
		return words;
	}
}
