import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	int capacity;
	Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
	LinkList<Integer> keyList = new LinkList<Integer>();
	Map<Integer, Node<Integer>> key2node = new HashMap<Integer, Node<Integer>>();

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		}
		keyList.moveToHead(key2node.get(key));
		return cache.get(key);
	}

	public void set(int key, int value) {
		if (cache.containsKey(key)) {
			keyList.moveToHead(key2node.get(key));
		} else {
			if (cache.size() == capacity) {
				Node<Integer> tail = keyList.tail;
				keyList.remove(tail);
				key2node.remove(tail.value);
				cache.remove(tail.value);
			}
			Node<Integer> node = new Node<Integer>(key);
			keyList.insertToHead(node);
			key2node.put(key, node);
		}
		cache.put(key, value);
	}
}

class Node<T> {
	T value;
	Node<T> prev;
	Node<T> next;

	Node(T value) {
		this.value = value;
	}
}

class LinkList<T> {
	Node<T> head;
	Node<T> tail;

	void remove(Node<T> node) {
		if (node != head && node != tail) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		} else {
			if (node == head) {
				head = node.next;
				if (head != null) {
					head.prev = null;
				}
			}
			if (node == tail) {
				tail = node.prev;
				if (tail != null) {
					tail.next = null;
				}
			}
		}
	}

	void insertToHead(Node<T> node) {
		node.next = head;
		node.prev = null;
		if (head == null) {
			tail = node;
		} else {
			head.prev = node;
		}
		head = node;
	}

	void moveToHead(Node<T> node) {
		remove(node);
		insertToHead(node);
	}
}

// Your LRUCache object will be instantiated and called as such:
// LRUCache obj = new LRUCache(capacity);
// int param_1 = obj.get(key);
// obj.put(key,value);
