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
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode tempHead = new ListNode(0);
		tempHead.next = head;

		ListNode reversedPrev = tempHead;
		ListNode reversedTail = null;
		ListNode p = head;
		for (int i = 1; i <= n; i++) {
			ListNode nextP = p.next;
			if (i < m) {
				reversedPrev = reversedPrev.next;
			} else if (i == m) {
				reversedTail = p;
			} else if (i <= n) {
				reversedTail.next = p.next;
				p.next = reversedPrev.next;
				reversedPrev.next = p;
			}
			p = nextP;
		}

		return tempHead.next;
	}
}
