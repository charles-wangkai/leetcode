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
  public int[] nodesBetweenCriticalPoints(ListNode head) {
    int minDistance = Integer.MAX_VALUE;
    int maxDistance = Integer.MIN_VALUE;
    int firstIndex = -1;
    int prevIndex = -1;
    int index = 0;
    for (ListNode prev = head, node = head.next; node.next != null; prev = node, node = node.next) {
      if ((node.val > prev.val && node.val > node.next.val)
          || (node.val < prev.val && node.val < node.next.val)) {
        if (firstIndex == -1) {
          firstIndex = index;
        } else {
          minDistance = Math.min(minDistance, index - prevIndex);
          maxDistance = index - firstIndex;
        }

        prevIndex = index;
      }

      ++index;
    }

    return (minDistance == Integer.MAX_VALUE)
        ? new int[] {-1, -1}
        : new int[] {minDistance, maxDistance};
  }
}
