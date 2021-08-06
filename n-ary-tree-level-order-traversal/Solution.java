import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
  public int val;
  public List<Node> children;

  public Node() {}

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}

class Solution {
  public List<List<Integer>> levelOrder(Node root) {
    if (root == null) {
      return List.of();
    }

    List<List<Integer>> levels = new ArrayList<>();
    search(levels, root, 0);

    return levels;
  }

  void search(List<List<Integer>> levels, Node node, int depth) {
    if (depth == levels.size()) {
      levels.add(new ArrayList<>());
    }
    levels.get(depth).add(node.val);

    for (Node child : node.children) {
      search(levels, child, depth + 1);
    }
  }
}
