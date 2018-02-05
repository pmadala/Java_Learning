package org.Collections.LRU;

/**
 * A node class representing each entry in LRU
 * An implementation fo double linked list 
 * @author priyambadam
 *
 */
class Node{
    int key;
    int value;
    Node pre;
    Node next;
 
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
    
    public String toString() {
    	return String.valueOf(value);
    }
}
