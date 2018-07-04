// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public ListNode plusOne(ListNode head) {
		ListNode result;
		if (increment(head)) {
			result = new ListNode(1);
			result.next = head;
		} else {
			result = head;
		}
		return result;
	}

	private boolean increment(ListNode node) {
		if (node.next == null || increment(node.next)) {
			node.val++;
		}

		if (node.val == 10) {
			node.val = 0;
			return true;
		} else {
			return false;
		}
	}
}
