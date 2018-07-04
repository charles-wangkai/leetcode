// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public ListNode reverseList(ListNode head) {
		ListNode reversed = null;
		while (head != null) {
			ListNode node = head;
			head = head.next;
			node.next = reversed;
			reversed = node;
		}
		return reversed;
	}
}
