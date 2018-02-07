package org.Multithreading.task4;

/* Trie is an efficient information reTrieval data structure. Using Trie, search complexities can be brought to optimal limit (key length). If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N, where M is maximum string length and N is number of keys in tree. Using Trie, we can search the key in O(M) time. However the penalty is on Trie storage requirements. */
import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	Map<Character, TrieNode> children;
	boolean endOfWord;

	public TrieNode() {
		children = new HashMap<>();
	}

	@Override
	public String toString() {
		return "TrieNode [children=" + children + ", endOfWord=" + endOfWord + "]";
	}
}
