// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class ReorderList {
	public void reorderList(ListNode head) {
		if (head == null) {
			return;
		}

		int length = findLength(head);
		ListNode node = head;
		for (int i = 0; i < (length - 1) / 2; i++) {
			node = node.next;
		}
		ListNode backHead = node.next;
		node.next = null;

		backHead = reverse(backHead);

		for (ListNode p = head; backHead != null;) {
			ListNode q = backHead;
			backHead = backHead.next;
			q.next = p.next;
			p.next = q;
			p = q.next;
		}
	}

	int findLength(ListNode head) {
		int length = 0;
		for (ListNode node = head; node != null; node = node.next) {
			length++;
		}
		return length;
	}

	ListNode reverse(ListNode head) {
		ListNode tempHead = new ListNode(0);
		while (head != null) {
			ListNode node = head;
			head = head.next;
			node.next = tempHead.next;
			tempHead.next = node;
		}
		return tempHead.next;
	}
}
