import java.util.ArrayDeque;
import java.util.Deque;

// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  public int[] nextLargerNodes(ListNode head) {
    Deque<Integer> values = new ArrayDeque<>();
    for (ListNode node = head; node != null; node = node.next) {
      values.push(node.val);
    }

    int[] result = new int[values.size()];
    Deque<Integer> largers = new ArrayDeque<>();
    for (int i = result.length - 1; i >= 0; i--) {
      int value = values.pop();

      while (!largers.isEmpty() && largers.peek() <= value) {
        largers.pop();
      }

      result[i] = largers.isEmpty() ? 0 : largers.peek();

      largers.push(value);
    }
    return result;
  }
}
