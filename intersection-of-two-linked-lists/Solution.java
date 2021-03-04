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
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lengthA = computeLength(headA);
    int lengthB = computeLength(headB);
    ListNode nodeA = move(headA, Math.max(0, lengthA - lengthB));
    ListNode nodeB = move(headB, Math.max(0, lengthB - lengthA));
    while (nodeA != nodeB) {
      nodeA = nodeA.next;
      nodeB = nodeB.next;
    }

    return nodeA;
  }

  int computeLength(ListNode head) {
    int length = 0;
    while (head != null) {
      ++length;
      head = head.next;
    }

    return length;
  }

  ListNode move(ListNode head, int step) {
    ListNode node = head;
    for (int i = 0; i < step; ++i) {
      node = node.next;
    }

    return node;
  }
}
