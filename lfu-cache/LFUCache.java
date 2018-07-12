import java.util.HashMap;
import java.util.Map;

public class LFUCache {
	int capacity;

	Map<Integer, Integer> key2value = new HashMap<Integer, Integer>();

	Map<Integer, Integer> key2freq = new HashMap<Integer, Integer>();
	LinkList<Integer> freqs = new LinkList<Integer>();
	Map<Integer, LinkList<Integer>> freq2keys = new HashMap<Integer, LinkList<Integer>>();

	Map<Integer, Node<Integer>> key2node = new HashMap<Integer, Node<Integer>>();
	Map<Integer, Node<Integer>> freq2node = new HashMap<Integer, Node<Integer>>();

	public LFUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!key2value.containsKey(key)) {
			return -1;
		}

		int freq = key2freq.get(key);

		int newFreq = freq + 1;
		if (!freq2keys.containsKey(newFreq)) {
			freq2keys.put(newFreq, new LinkList<Integer>());

			Node<Integer> node = new Node<Integer>(newFreq);
			freq2node.put(newFreq, node);

			Node<Integer> prevNode = freq2node.get(freq);
			Node<Integer> nextNode = prevNode.next;

			prevNode.next = node;
			node.next = nextNode;

			if (nextNode != null) {
				nextNode.prev = node;
			}
			node.prev = prevNode;
		}

		remove(freq, key);
		add(newFreq, key);

		return key2value.get(key);
	}

	public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}

		if (key2value.containsKey(key)) {
			get(key);
		} else {
			if (key2value.size() == capacity) {
				int minFreq = freqs.head.value;
				int keyToRemove = freq2keys.get(minFreq).tail.value;

				remove(minFreq, keyToRemove);

				removeKey(keyToRemove);
			}

			int newFreq = 1;
			if (!freq2keys.containsKey(newFreq)) {
				freq2keys.put(newFreq, new LinkList<Integer>());

				Node<Integer> node = new Node<Integer>(newFreq);
				freq2node.put(newFreq, node);

				insertToHead(freqs, node);
			}

			add(newFreq, key);
		}

		key2value.put(key, value);
	}

	private void removeKey(int key) {
		key2value.remove(key);
		key2freq.remove(key);
		key2node.remove(key);
	}

	private void remove(int freq, int key) {
		LinkList<Integer> keys = freq2keys.get(freq);

		removeFromList(keys, key2node.get(key));

		if (keys.head == null) {
			freq2keys.remove(freq);

			removeFromList(freqs, freq2node.get(freq));
		}
	}

	private <T> void removeFromList(LinkList<T> list, Node<T> node) {
		if (node != list.head && node != list.tail) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		} else {
			if (node == list.head) {
				list.head = node.next;
				if (list.head != null) {
					list.head.prev = null;
				}
			}
			if (node == list.tail) {
				list.tail = node.prev;
				if (list.tail != null) {
					list.tail.next = null;
				}
			}
		}
	}

	private <T> void insertToHead(LinkList<T> list, Node<T> node) {
		node.next = list.head;
		node.prev = null;
		if (list.head == null) {
			list.tail = node;
		} else {
			list.head.prev = node;
		}
		list.head = node;
	}

	private void add(int freq, int key) {
		LinkList<Integer> keys = freq2keys.get(freq);

		Node<Integer> node = new Node<Integer>(key);
		key2node.put(key, node);

		insertToHead(keys, node);

		key2freq.put(key, freq);
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
}

// Your LFUCache object will be instantiated and called as such:
// LFUCache obj = new LFUCache(capacity);
// int param_1 = obj.get(key);
// obj.put(key,value);
