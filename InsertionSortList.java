// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		ListNode tempHead = new ListNode(0);
		while (head != null) {
			ListNode node = head;
			head = node.next;
			node.next = null;

			ListNode p = tempHead;
			while (p.next != null && p.next.val < node.val) {
				p = p.next;
			}
			node.next = p.next;
			p.next = node;
		}
		return tempHead.next;
	}
}
