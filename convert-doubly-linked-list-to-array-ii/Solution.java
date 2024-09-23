import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
  public int val;
  public Node prev;
  public Node next;
}

class Solution {
  public int[] toArray(Node node) {
    Node head = node;
    while (head.prev != null) {
      head = head.prev;
    }

    List<Integer> values = new ArrayList<>();
    for (Node p = head; p != null; p = p.next) {
      values.add(p.val);
    }

    return values.stream().mapToInt(Integer::intValue).toArray();
  }
}