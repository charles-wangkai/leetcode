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
  public ListNode sortList(ListNode head) {
    int length = findLength(head);
    ListNode tempHead = new ListNode();
    tempHead.next = head;
    for (int halfLength = 1; halfLength < length; halfLength *= 2) {
      ListNode ahead = tempHead;
      while (ahead.next != null) {
        ahead.next = merge(ahead.next, halfLength);
        for (int i = 0; i < halfLength * 2 && ahead.next != null; ++i) {
          ahead = ahead.next;
        }
      }
    }

    return tempHead.next;
  }

  int findLength(ListNode head) {
    int length = 0;
    while (head != null) {
      ++length;
      head = head.next;
    }

    return length;
  }

  ListNode merge(ListNode head, int halfLength) {
    ListNode head1 = head;
    ListNode p = head1;
    for (int i = 0; i < halfLength - 1 && p.next != null; ++i) {
      p = p.next;
    }
    ListNode head2 = p.next;
    if (head2 == null) {
      return head;
    }
    p.next = null;

    ListNode q = head2;
    for (int i = 0; i < halfLength - 1 && q.next != null; ++i) {
      q = q.next;
    }
    ListNode after = q.next;
    q.next = null;

    ListNode tempHead = new ListNode();
    tempHead.next = after;
    ListNode r = tempHead;
    while (head1 != null || head2 != null) {
      ListNode node;
      if (head1 != null && (head2 == null || head1.val <= head2.val)) {
        node = head1;
        head1 = head1.next;
      } else {
        node = head2;
        head2 = head2.next;
      }
      node.next = r.next;
      r.next = node;
      r = r.next;
    }

    return tempHead.next;
  }
}
