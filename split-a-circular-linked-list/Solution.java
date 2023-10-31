// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public ListNode[] splitCircularLinkedList(ListNode list) {
    int length = 1;
    ListNode rightTail = list;
    while (rightTail.next != list) {
      ++length;
      rightTail = rightTail.next;
    }

    int leftLength = (length + 1) / 2;

    ListNode leftTail = list;
    for (int i = 0; i < leftLength - 1; ++i) {
      leftTail = leftTail.next;
    }

    ListNode rightHead = leftTail.next;

    leftTail.next = list;
    rightTail.next = rightHead;

    return new ListNode[] {list, rightHead};
  }
}
