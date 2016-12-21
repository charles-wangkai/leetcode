// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class AddTwoNumbers_II {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int length1 = getLength(l1);
		int length2 = getLength(l2);
		int maxLength = Math.max(length1, length2);

		ListNode sum = new ListNode(0);
		int carry = add(length1 - maxLength, l1, length2 - maxLength, l2, sum);
		if (carry > 0) {
			prepend(sum, carry);
		}
		return sum.next;
	}

	int add(int index1, ListNode node1, int index2, ListNode node2, ListNode sum) {
		int digit1;
		ListNode nextNode1;
		if (index1 >= 0) {
			digit1 = node1.val;
			nextNode1 = node1.next;
		} else {
			digit1 = 0;
			nextNode1 = node1;
		}

		int digit2;
		ListNode nextNode2;
		if (index2 >= 0) {
			digit2 = node2.val;
			nextNode2 = node2.next;
		} else {
			digit2 = 0;
			nextNode2 = node2;
		}

		int carry = 0;
		if (nextNode1 != null || nextNode2 != null) {
			carry = add(index1 + 1, nextNode1, index2 + 1, nextNode2, sum);
		}

		int s = carry + digit1 + digit2;
		prepend(sum, s % 10);
		return s / 10;
	}

	int getLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}

	void prepend(ListNode sum, int digit) {
		ListNode node = new ListNode(digit);
		node.next = sum.next;
		sum.next = node;
	}
}
