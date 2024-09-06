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
  public String gameResult(ListNode head) {
    int evenPoint = 0;
    int oddPoint = 0;
    ListNode p = head;
    while (p != null) {
      if (p.val > p.next.val) {
        ++evenPoint;
      } else {
        ++oddPoint;
      }

      p = p.next.next;
    }

    if (evenPoint < oddPoint) {
      return "Odd";
    }
    if (evenPoint > oddPoint) {
      return "Even";
    }

    return "Tie";
  }
}