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
  public void reorderList(ListNode head) {
    int length = computeLength(head);

    ListNode node = head;
    for (int i = 0; i < (length - 1) / 2; ++i) {
      node = node.next;
    }
    ListNode backHead = node.next;
    node.next = null;
    backHead = reverse(backHead);

    ListNode p = head;
    while (backHead != null) {
      ListNode q = backHead;
      backHead = backHead.next;
      q.next = p.next;
      p.next = q;
      p = q.next;
    }
  }

  int computeLength(ListNode head) {
    int result = 0;
    for (ListNode node = head; node != null; node = node.next) {
      ++result;
    }

    return result;
  }

  ListNode reverse(ListNode head) {
    ListNode tempHead = new ListNode();
    while (head != null) {
      ListNode node = head;
      head = head.next;
      node.next = tempHead.next;
      tempHead.next = node;
    }

    return tempHead.next;
  }
}
