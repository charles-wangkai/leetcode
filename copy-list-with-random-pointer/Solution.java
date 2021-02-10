import java.util.HashMap;
import java.util.Map;

// Definition for a Node.
class Node {
  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }
}

class Solution {
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    Map<Node, Node> cloneMap = new HashMap<>();

    Node clonedHead = new Node(head.val);
    cloneMap.put(head, clonedHead);
    for (Node node = head, clonedNode = clonedHead;
        node.next != null;
        node = node.next, clonedNode = clonedNode.next) {
      clonedNode.next = new Node(node.next.val);
      cloneMap.put(node.next, clonedNode.next);
    }

    for (Node node = head, clonedNode = clonedHead;
        node != null;
        node = node.next, clonedNode = clonedNode.next) {
      clonedNode.random = cloneMap.get(node.random);
    }

    return clonedHead;
  }
}
