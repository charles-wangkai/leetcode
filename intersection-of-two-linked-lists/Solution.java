// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int lengthA = findLength(headA);
		int lengthB = findLength(headB);
		ListNode nodeA = move(headA, lengthA > lengthB ? (lengthA - lengthB)
				: 0);
		ListNode nodeB = move(headB, lengthB > lengthA ? (lengthB - lengthA)
				: 0);
		while (nodeA != nodeB) {
			nodeA = nodeA.next;
			nodeB = nodeB.next;
		}
		return nodeA;
	}

	int findLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}

	ListNode move(ListNode head, int step) {
		ListNode node = head;
		for (int i = 0; i < step; i++) {
			node = node.next;
		}
		return node;
	}
}
