public class MyLinkedList {
	private Node head;

	/** Initialize your data structure here. */
	public MyLinkedList() {
	}

	/**
	 * Get the value of the index-th node in the linked list. If the index is
	 * invalid, return -1.
	 */
	public int get(int index) {
		if (!(index >= 0 && index < getLength())) {
			return -1;
		}

		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.value;
	}

	/**
	 * Add a node of value val before the first element of the linked list. After
	 * the insertion, the new node will be the first node of the linked list.
	 */
	public void addAtHead(int val) {
		Node node = new Node(val);
		node.next = head;
		head = node;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		Node node = new Node(val);
		if (head == null) {
			head = node;
		} else {
			Node tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}
			tail.next = node;
		}
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If index
	 * equals to the length of linked list, the node will be appended to the end of
	 * linked list. If index is greater than the length, the node will not be
	 * inserted.
	 */
	public void addAtIndex(int index, int val) {
		if (!(index >= 0 && index <= getLength())) {
			return;
		}

		Node node = new Node(val);
		if (index == 0) {
			head = node;
		} else {
			Node prev = head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}

			node.next = prev.next;
			prev.next = node;
		}
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (!(index >= 0 && index < getLength())) {
			return;
		}

		if (index == 0) {
			head = head.next;
		} else {
			Node prev = head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}

			prev.next = prev.next.next;
		}
	}

	private int getLength() {
		int length = 0;
		Node node = head;
		while (node != null) {
			length++;
			node = node.next;
		}
		return length;
	}
}

class Node {
	int value;
	Node next;

	Node(int value) {
		this.value = value;
	}
}

// Your MyLinkedList object will be instantiated and called as such:
// MyLinkedList obj = new MyLinkedList();
// int param_1 = obj.get(index);
// obj.addAtHead(val);
// obj.addAtTail(val);
// obj.addAtIndex(index,val);
// obj.deleteAtIndex(index);
