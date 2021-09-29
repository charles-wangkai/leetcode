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
    int length = getLength(head);
    int averageLength = length / k;
    int extraPartNum = length % k;

    ListNode[] parts = new ListNode[k];
    for (int i = 0; i < parts.length; ++i) {
      ListNode tempHead = new ListNode();
      head = retrieve(head, tempHead, averageLength + ((i < extraPartNum) ? 1 : 0));
      parts[i] = tempHead.next;
    }

    return parts;
  }

  ListNode retrieve(ListNode head, ListNode tempHead, int partLength) {
    ListNode prev = tempHead;
    for (int i = 0; i < partLength; ++i) {
      ListNode node = head;
      head = head.next;
      node.next = null;
      prev.next = node;
      prev = node;
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
