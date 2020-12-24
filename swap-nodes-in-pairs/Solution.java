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
  public ListNode swapPairs(ListNode head) {
    ListNode tempHead = new ListNode();
    tempHead.next = head;

    ListNode current = tempHead;
    while (current.next != null && current.next.next != null) {
      ListNode node1 = current.next;
      ListNode node2 = node1.next;
      ListNode after = node2.next;
      current.next = node2;
      node2.next = node1;
      node1.next = after;
      current = current.next.next;
    }

    return tempHead.next;
  }
}
