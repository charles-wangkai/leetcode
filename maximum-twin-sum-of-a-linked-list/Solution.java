import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
  public int pairSum(ListNode head) {
    List<Integer> values = new ArrayList<>();
    for (ListNode p = head; p != null; p = p.next) {
      values.add(p.val);
    }

    return IntStream.range(0, values.size() / 2)
        .map(i -> values.get(i) + values.get(values.size() - 1 - i))
        .max()
        .getAsInt();
  }
}