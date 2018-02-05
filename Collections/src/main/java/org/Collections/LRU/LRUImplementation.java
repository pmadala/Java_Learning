package org.Collections.LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * An LRU implemenntaation for Node objects 
 * @author priyambadam
 *
 */
public class LRUImplementation {

	int capacity;
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	Node head = null;
	Node end = null;

	public LRUImplementation(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			remove(node);
			setHead(node);
			return node.value;
		}

		return -1;
	}

	/**
	 * Get the value (will always be positive) of the key if the key exists in the
	 * cache, otherwise return -1.
	 * 
	 * @param node
	 */
	public void remove(Node node) {
		if (node.pre != null) {
			node.pre.next = node.next;
		} else {
			head = node.next;
		}

		if (node.next != null) {
			node.next.pre = node.pre;
		} else {
			end = node.pre;
		}

	}

	public void setHead(Node node) {
		node.next = head;
		node.pre = null;

		if (head != null)
			head.pre = node;

		head = node;

		if (end == null)
			end = head;
	}

	/**
	 * Set or insert the value if the key is not already present. When the cache
	 * reached its capacity, it should invalidate the least recently used item
	 * before inserting a new item.
	 * 
	 * @param key
	 * @param value
	 */

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			Node old = map.get(key);
			old.value = value;
			remove(old);
			setHead(old);
		} else {
			Node created = new Node(key, value);
			if (map.size() >= capacity) {
				map.remove(end.key);
				remove(end);
				setHead(created);

			} else {
				setHead(created);
			}

			map.put(key, created);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Integer, Node> entry : map.entrySet()) {
			sb.append("Entry in LRU key : "+ entry.getKey()+ " and value : "+ entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main (String args[]) {
		
		LRUImplementation lru = new LRUImplementation(3);
		lru.set(1, 1);
		lru.set(2, 2);
		lru.set(3, 3);
		lru.set(2, 4);
		lru.set(5, 5);
		lru.set(3, 1);
		lru.set(2, 3);
		lru.set(1, 5);
		lru.set(2, 3);
		lru.set(3, 2);
		
		System.out.println(lru);
		
	}
}
