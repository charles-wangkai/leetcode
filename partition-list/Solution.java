// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public ListNode partition(ListNode head, int x) {
		ListNode[] leftList = new ListNode[2];
		ListNode[] rightList = new ListNode[2];

		ListNode p = head;
		while (p != null) {
			ListNode next = p.next;
			p.next = null;
			if (p.val < x) {
				leftList = appendList(leftList, p);
			} else {
				rightList = appendList(rightList, p);
			}
			p = next;
		}

		if (leftList[0] == null) {
			return rightList[0];
		} else {
			leftList[1].next = rightList[0];
			return leftList[0];
		}
	}

	ListNode[] appendList(ListNode[] list, ListNode node) {
		ListNode head = list[0];
		ListNode tail = list[1];
		if (head == null) {
			head = node;
		} else {
			tail.next = node;
		}
		tail = node;
		return new ListNode[] { head, tail };
	}
}
