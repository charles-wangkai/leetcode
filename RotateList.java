// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class RotateList {
	public ListNode rotateRight(ListNode head, int n) {
		int length = findLen(head);
		if (length == 0 || (n %= length) == 0) {
			return head;
		}

		ListNode leftTail = head;
		for (int i = 0; i < length - n - 1; i++) {
			leftTail = leftTail.next;
		}
		ListNode rightHead = leftTail.next;
		leftTail.next = null;
		ListNode rightTail = rightHead;
		while (rightTail.next != null) {
			rightTail = rightTail.next;
		}
		rightTail.next = head;
		return rightHead;
	}

	int findLen(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}
}
