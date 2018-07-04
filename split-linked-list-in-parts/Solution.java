// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public ListNode[] splitListToParts(ListNode root, int k) {
		int length = getLength(root);
		int averageLength = length / k;
		int extraPartNum = length % k;

		ListNode[] parts = new ListNode[k];
		for (int i = 0; i < parts.length; i++) {
			ListNode tempHead = new ListNode(0);
			root = retrieve(root, tempHead, averageLength + (i < extraPartNum ? 1 : 0));
			parts[i] = tempHead.next;
		}
		return parts;
	}

	ListNode retrieve(ListNode root, ListNode tempHead, int partLength) {
		ListNode prev = tempHead;
		for (int i = 0; i < partLength; i++) {
			ListNode node = root;
			root = root.next;
			node.next = null;
			prev.next = node;
			prev = node;
		}
		return root;
	}

	int getLength(ListNode root) {
		int length = 0;
		while (root != null) {
			root = root.next;
			length++;
		}
		return length;
	}
}
