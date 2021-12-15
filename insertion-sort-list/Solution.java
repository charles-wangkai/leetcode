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
  public ListNode insertionSortList(ListNode head) {
    ListNode tempHead = new ListNode();

    while (head != null) {
      ListNode node = head;
      head = node.next;

      ListNode p = tempHead;
      while (p.next != null && p.next.val < node.val) {
        p = p.next;
      }
      node.next = p.next;
      p.next = node;
    }

    return tempHead.next;
  }
}
