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
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode tempHead = new ListNode(0);
		tempHead.next = head;

		ListNode current = tempHead;
		while (true) {
			ListNode rHead = null;
			ListNode rTail = null;

			ListNode p = current;
			for (int i = 0; i < k; i++) {
				if (p.next == null) {
					return tempHead.next;
				}
				p = p.next;
			}

			for (int i = 0; i < k; i++) {
				ListNode node = current.next;
				current.next = node.next;
				node.next = rHead;
				rHead = node;
				if (rTail == null) {
					rTail = node;
				}
			}

			ListNode after = current.next;
			current.next = rHead;
			rTail.next = after;

			for (int i = 0; i < k; i++) {
				current = current.next;
			}
		}
	}
}
