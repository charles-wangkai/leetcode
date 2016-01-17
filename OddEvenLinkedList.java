// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
		ListNode tempHead = new ListNode(0);
		tempHead.next = head;

		ListNode evenHead = new ListNode(0);
		evenHead.next = null;
		ListNode evenTail = evenHead;

		ListNode node = tempHead;
		boolean oddOrEven = true;
		while (node.next != null) {
			if (!oddOrEven) {
				evenTail.next = node.next;
				node.next = node.next.next;
				evenTail.next.next = null;
				evenTail = evenTail.next;
			} else {
				node = node.next;
			}
			oddOrEven = !oddOrEven;
		}
		node.next = evenHead.next;

		return tempHead.next;
	}
}
