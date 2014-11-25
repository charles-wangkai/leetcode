// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class RemoveDuplicatesFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode p = head;
		while (p != null) {
			while (p.next != null && p.val == p.next.val) {
				p.next = p.next.next;
			}
			p = p.next;
		}
		return head;
	}
}
