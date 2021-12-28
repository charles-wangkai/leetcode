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
  public ListNode middleNode(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;

    while (true) {
      if (fast == null || fast.next == null) {
        return slow;
      }

      fast = fast.next.next;
      slow = slow.next;
    }
  }
}
