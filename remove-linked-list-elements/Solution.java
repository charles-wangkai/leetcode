// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

class Solution {
	public ListNode removeElements(ListNode head, int val) {
		ListNode tempHead = new ListNode();
		tempHead.next = head;

		ListNode p = tempHead;
		while (p.next != null) {
			if (p.next.val == val) {
				p.next = p.next.next;
			} else {
				p = p.next;
			}
		}

		return tempHead.next;
	}
}
