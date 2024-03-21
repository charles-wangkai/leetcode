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
  public ListNode reverseList(ListNode head) {
    ListNode result = null;
    while (head != null) {
      ListNode node = head;
      head = head.next;
      node.next = result;
      result = node;
    }

    return result;
  }
}
