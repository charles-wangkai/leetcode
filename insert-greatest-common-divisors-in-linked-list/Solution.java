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
  public ListNode insertGreatestCommonDivisors(ListNode head) {
    ListNode curr = head;
    while (curr.next != null) {
      ListNode next = curr.next;
      curr.next = new ListNode(gcd(curr.val, next.val), next);

      curr = next;
    }

    return head;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
