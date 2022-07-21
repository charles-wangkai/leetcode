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
  public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode tempHead = new ListNode(0, head);

    ListNode reversedPrev = tempHead;
    ListNode reversedTail = null;
    ListNode p = head;
    for (int i = 1; i <= right; ++i) {
      ListNode next = p.next;
      if (i < left) {
        reversedPrev = reversedPrev.next;
      } else if (i == left) {
        reversedTail = p;
      } else {
        reversedTail.next = p.next;
        p.next = reversedPrev.next;
        reversedPrev.next = p;
      }

      p = next;
    }

    return tempHead.next;
  }
}
