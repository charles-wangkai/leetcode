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
  public ListNode frequenciesOfElements(ListNode head) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (ListNode p = head; p != null; p = p.next) {
      valueToCount.put(p.val, valueToCount.getOrDefault(p.val, 0) + 1);
    }

    ListNode tempHead = new ListNode();
    for (int count : valueToCount.values()) {
      ListNode node = new ListNode(count, tempHead.next);
      tempHead.next = node;
    }

    return tempHead.next;
  }
}