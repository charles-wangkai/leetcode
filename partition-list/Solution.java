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
  public ListNode partition(ListNode head, int x) {
    ListNode tempLeftHead = new ListNode();
    ListNode tempRightHead = new ListNode();

    ListNode p = head;
    while (p != null) {
      ListNode next = p.next;

      if (p.val < x) {
        p.next = tempLeftHead.next;
        tempLeftHead.next = p;
      } else {
        p.next = tempRightHead.next;
        tempRightHead.next = p;
      }

      p = next;
    }

    ListNode tempHead = new ListNode();
    while (tempRightHead.next != null) {
      ListNode node = tempRightHead.next;
      tempRightHead.next = node.next;

      node.next = tempHead.next;
      tempHead.next = node;
    }
    while (tempLeftHead.next != null) {
      ListNode node = tempLeftHead.next;
      tempLeftHead.next = node.next;

      node.next = tempHead.next;
      tempHead.next = node;
    }

    return tempHead.next;
  }
}
