import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] simulationResult(int[] windows, int[] queries) {
    Node tempHead = new Node(-1);
    Map<Integer, Node> idToNode = new HashMap<>();
    for (int i = windows.length - 1; i >= 0; --i) {
      Node node = new Node(windows[i]);
      idToNode.put(windows[i], node);

      insertToHead(tempHead, node);
    }

    for (int query : queries) {
      Node node = idToNode.get(query);

      node.prev.next = node.next;
      if (node.next != null) {
        node.next.prev = node.prev;
      }

      insertToHead(tempHead, node);
    }

    int[] result = new int[windows.length];
    Node p = tempHead;
    for (int i = 0; i < result.length; ++i) {
      result[i] = p.next.id;
      p = p.next;
    }

    return result;
  }

  void insertToHead(Node tempHead, Node node) {
    node.next = tempHead.next;
    if (tempHead.next != null) {
      tempHead.next.prev = node;
    }
    tempHead.next = node;
    node.prev = tempHead;
  }
}

class Node {
  int id;
  Node prev;
  Node next;

  Node(int id) {
    this.id = id;
  }
}