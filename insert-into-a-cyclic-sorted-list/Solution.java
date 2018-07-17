// Definition for a Node.
class Node {
	public int val;
	public Node next;

	public Node() {
	}

	public Node(int _val, Node _next) {
		val = _val;
		next = _next;
	}
}

public class Solution {
	public Node insert(Node head, int insertVal) {
		if (head == null) {
			Node node = new Node();
			node.val = insertVal;
			node.next = node;

			return node;
		}

		int minValue = head.val;
		int maxValue = head.val;
		for (Node node = head.next; node != head; node = node.next) {
			minValue = Math.min(minValue, node.val);
			maxValue = Math.max(maxValue, node.val);
		}

		Node prev = head;
		if (minValue != maxValue) {
			if (insertVal < minValue || insertVal >= maxValue) {
				while (!(prev.val != minValue && prev.next.val == minValue)) {
					prev = prev.next;
				}
			} else {
				while (!(insertVal >= prev.val && insertVal <= prev.next.val)) {
					prev = prev.next;
				}
			}
		}
		insertAt(prev, insertVal);

		return head;
	}

	void insertAt(Node prev, int insertVal) {
		Node node = new Node(insertVal, prev.next);
		prev.next = node;
	}
}
