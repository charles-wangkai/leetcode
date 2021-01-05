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
  public ListNode deleteDuplicates(ListNode head) {
    ListNode tempHead = new ListNode();
    tempHead.next = head;

    ListNode p = tempHead;
    while (p.next != null && p.next.next != null) {
      if (p.next.val != p.next.next.val) {
        p = p.next;
      } else {
        int removedVal = p.next.val;
        while (p.next != null && p.next.val == removedVal) {
          p.next = p.next.next;
        }
      }
    }

    return tempHead.next;
  }
}
