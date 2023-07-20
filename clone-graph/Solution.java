import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Definition for a Node.
class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    val = 0;
    neighbors = new ArrayList<>();
  }

  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<>();
  }

  public Node(int _val, List<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }
}

class Solution {
  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }

    Map<Node, Node> originToCopy = new HashMap<>();
    Set<Node> visited = new HashSet<>();
    clone(originToCopy, visited, node);

    return originToCopy.get(node);
  }

  void clone(Map<Node, Node> originToCopy, Set<Node> visited, Node node) {
    visited.add(node);
    Node cloned = new Node(node.val);
    originToCopy.put(node, cloned);

    for (Node neighbor : node.neighbors) {
      if (!visited.contains(neighbor)) {
        clone(originToCopy, visited, neighbor);
      }

      cloned.neighbors.add(originToCopy.get(neighbor));
    }
  }
}
