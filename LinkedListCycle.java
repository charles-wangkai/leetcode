// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		boolean first = true;
		for (ListNode fast = head, slow = head;; fast = fast.next.next, slow = slow.next) {
			if (fast == null || fast.next == null) {
				return false;
			}
			if (!first && fast == slow) {
				return true;
			}
			first = false;
		}
	}
}
