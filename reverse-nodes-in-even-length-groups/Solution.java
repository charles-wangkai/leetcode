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
  public ListNode reverseEvenLengthGroups(ListNode head) {
    ListNode prev = head;
    for (int i = 2; ; ++i) {
      ListNode groupHead = prev.next;
      if (groupHead == null) {
        break;
      }

      int length = 1;
      ListNode groupTail = groupHead;
      while (length < i && groupTail.next != null) {
        groupTail = groupTail.next;
        ++length;
      }

      if (length % 2 == 0) {
        ListNode groupNext = groupTail.next;
        while (groupHead.next != groupNext) {
          ListNode node = groupHead.next;
          groupHead.next = node.next;
          node.next = prev.next;
          prev.next = node;
        }
        prev = groupHead;
      } else {
        prev = groupTail;
      }
    }

    return head;
  }
}
