// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public int getDecimalValue(ListNode head) {
		int result = 0;
		for (ListNode p = head; p != null; p = p.next) {
			result = result * 2 + p.val;
		}

		return result;
	}
}
