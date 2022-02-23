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
  public ListNode sortLinkedList(ListNode head) {
    ListNode tempHead = new ListNode();
    ListNode tail = tempHead;
    ListNode p = head;
    while (p != null) {
      ListNode next = p.next;
      if (p.val >= 0) {
        tail.next = p;
        p.next = null;
        tail = p;
      } else {
        if (tail == tempHead) {
          tail = p;
        }
        p.next = tempHead.next;
        tempHead.next = p;
      }

      p = next;
    }

    return tempHead.next;
  }
}