// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public boolean isPalindrome(ListNode head) {
		return isMatch(head, reverse(findRightHalf(head)));
	}

	ListNode findRightHalf(ListNode head) {
		int length = findLength(head);
		ListNode rightHead = head;
		for (int i = 0; i < (length + 1) / 2; i++) {
			rightHead = rightHead.next;
		}
		return rightHead;
	}

	int findLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}

	ListNode reverse(ListNode head) {
		ListNode tempHead = new ListNode(0);
		tempHead.next = null;
		while (head != null) {
			ListNode node = head;
			head = head.next;
			node.next = tempHead.next;
			tempHead.next = node;
		}
		return tempHead.next;
	}

	boolean isMatch(ListNode head1, ListNode head2) {
		while (head1 != null && head2 != null) {
			if (head1.val != head2.val) {
				return false;
			}
			head1 = head1.next;
			head2 = head2.next;
		}
		return true;
	}
}
