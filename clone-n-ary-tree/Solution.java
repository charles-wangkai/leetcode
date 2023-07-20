import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definition for a Node.
class Node {
  public int val;
  public List<Node> children;

  public Node() {
    children = new ArrayList<>();
  }

  public Node(int _val) {
    val = _val;
    children = new ArrayList<>();
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}

class Solution {
  public Node cloneTree(Node root) {
    if (root == null) {
      return null;
    }

    Map<Node, Node> originToCopy = new HashMap<>();
    clone(originToCopy, root);

    return originToCopy.get(root);
  }

  void clone(Map<Node, Node> originToCopy, Node node) {
    Node copy = new Node(node.val);
    originToCopy.put(node, copy);
    for (Node child : node.children) {
      clone(originToCopy, child);
      copy.children.add(originToCopy.get(child));
    }
  }
}
