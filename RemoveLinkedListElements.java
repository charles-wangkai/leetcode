// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class RemoveLinkedListElements {
	public ListNode removeElements(ListNode head, int val) {
		ListNode tempHead = new ListNode(0);
		tempHead.next = head;
		for (ListNode p = tempHead; p.next != null;) {
			if (p.next.val == val) {
				p.next = p.next.next;
			} else {
				p = p.next;
			}
		}
		return tempHead.next;
	}
}
