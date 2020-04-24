import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private int capacity;
	private Map<Integer, Integer> cache = new HashMap<>();
	private LinkList<Integer> keys = new LinkList<>();
	private Map<Integer, Node<Integer>> keyToNode = new HashMap<>();

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		}

		keys.moveToHead(keyToNode.get(key));

		return cache.get(key);
	}

	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			keys.moveToHead(keyToNode.get(key));
		} else {
			if (cache.size() == capacity) {
				Node<Integer> tail = keys.tail;
				keys.remove(tail);
				keyToNode.remove(tail.value);
				cache.remove(tail.value);
			}

			Node<Integer> node = new Node<>(key);
			keys.insertToHead(node);
			keyToNode.put(key, node);
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
