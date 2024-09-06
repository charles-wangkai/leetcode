import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
  public ListNode modifiedList(int[] nums, ListNode head) {
    Set<Integer> values = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    ListNode tempHead = new ListNode(0, head);
    ListNode p = tempHead;
    while (p.next != null) {
      if (values.contains(p.next.val)) {
        p.next = p.next.next;
      } else {
        p = p.next;
      }
    }

    return tempHead.next;
  }
}