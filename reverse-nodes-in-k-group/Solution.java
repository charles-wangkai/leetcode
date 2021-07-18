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
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode tempHead = new ListNode(0, head);

    ListNode current = tempHead;
    while (true) {
      ListNode p = current;
      for (int i = 0; i < k; ++i) {
        if (p.next == null) {
          return tempHead.next;
        }

        p = p.next;
      }

      ListNode rHead = null;
      ListNode rTail = null;
      for (int i = 0; i < k; ++i) {
        ListNode node = current.next;
        current.next = node.next;

        node.next = rHead;
        rHead = node;

        if (rTail == null) {
          rTail = node;
        }
      }

      ListNode after = current.next;
      current.next = rHead;
      rTail.next = after;

      for (int i = 0; i < k; ++i) {
        current = current.next;
      }
    }
  }
}
