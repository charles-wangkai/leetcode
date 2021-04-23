import java.util.HashMap;
import java.util.Map;

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
  public ListNode deleteDuplicatesUnsorted(ListNode head) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (ListNode node = head; node != null; node = node.next) {
      valueToCount.put(node.val, valueToCount.getOrDefault(node.val, 0) + 1);
    }

    ListNode tempHead = new ListNode(0, head);
    ListNode node = tempHead;
    while (node.next != null) {
      if (valueToCount.get(node.next.val) != 1) {
        node.next = node.next.next;
      } else {
        node = node.next;
      }
    }

    return tempHead.next;
  }
}
