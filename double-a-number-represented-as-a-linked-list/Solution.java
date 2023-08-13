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
  public ListNode doubleIt(ListNode head) {
    int carry = update(head);
    if (carry == 1) {
      head = new ListNode(1, head);
    }

    return head;
  }

  int update(ListNode node) {
    if (node == null) {
      return 0;
    }

    int value = node.val * 2 + update(node.next);
    node.val = value % 10;

    return value / 10;
  }
}
