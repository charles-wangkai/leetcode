import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
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
  public ListNode removeZeroSumSublists(ListNode head) {
    List<Integer> sums = new ArrayList<>();
    sums.add(0);

    Map<Integer, Integer> sumToLength = new HashMap<>();
    sumToLength.put(0, 0);

    Deque<ListNode> stack = new ArrayDeque<>();
    int sum = 0;

    for (ListNode node = head; node != null; node = node.next) {
      sum += node.val;
      if (sumToLength.containsKey(sum)) {
        int length = sumToLength.get(sum);
        while (sums.size() != length + 1) {
          stack.pop();

          sumToLength.remove(sums.get(sums.size() - 1));
          sums.remove(sums.size() - 1);
        }
      } else {
        stack.push(node);

        sums.add(sum);
        sumToLength.put(sum, sumToLength.size());
      }
    }

    ListNode result = null;
    while (!stack.isEmpty()) {
      ListNode node = stack.pop();
      node.next = result;
      result = node;
    }

    return result;
  }
}
