// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

class Solution {
  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (true) {
      if (fast == null || fast.next == null) {
        return false;
      }

      slow = slow.next;
      fast = fast.next.next;

      if (fast == slow) {
        return true;
      }
    }
  }
}
