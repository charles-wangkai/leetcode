// Definition for a Node.
class Node {
	public int val;
	public Node prev;
	public Node next;
	public Node child;

	public Node() {
	}

	public Node(int _val, Node _prev, Node _next, Node _child) {
		val = _val;
		prev = _prev;
		next = _next;
		child = _child;
	}
}

public class Solution {
	public Node flatten(Node head) {
		Node node = head;
		while (node != null) {
			Node originalNext = node.next;

			Node child = flatten(node.child);
			if (child != null) {
				node.child = null;

				node.next = child;
				child.prev = node;

				Node tail = node;
				while (tail.next != null) {
					tail = tail.next;
				}
				tail.next = originalNext;
				if (originalNext != null) {
					originalNext.prev = tail;
				}
			}

			node = originalNext;
		}

		return head;
	}
}
