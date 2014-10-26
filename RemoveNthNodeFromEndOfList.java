// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode front = head;
		for (int i = 0; i < n; i++) {
			front = front.next;
		}

		if (front == null) {
			return head.next;
		}

		ListNode back = head;
		while (front.next != null) {
			front = front.next;
			back = back.next;
		}
		back.next = back.next.next;
		return head;
	}
}
