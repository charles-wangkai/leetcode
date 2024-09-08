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
  public ListNode[] splitListToParts(ListNode head, int k) {
    ListNode[] result = new ListNode[k];
    int length = getLength(head);
    for (int i = 0; i < result.length; ++i) {
      ListNode tempHead = new ListNode();
      head = retrieve(head, tempHead, length / k + ((i < length % k) ? 1 : 0));
      result[i] = tempHead.next;
    }

    return result;
  }

  ListNode retrieve(ListNode head, ListNode tempHead, int partLength) {
    ListNode tail = tempHead;
    for (int i = 0; i < partLength; ++i) {
      ListNode node = head;
      head = head.next;
      node.next = null;
      tail.next = node;
      tail = node;
    }

    return head;
  }

  int getLength(ListNode head) {
    int length = 0;
    while (head != null) {
      ++length;
      head = head.next;
    }

    return length;
  }
}
