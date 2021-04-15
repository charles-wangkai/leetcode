import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
  public int val;
  public Node left;
  public Node right;
  public Node parent;
}

class Solution {
  public Node flipBinaryTree(Node root, Node leaf) {
    List<Node> path = new ArrayList<>();
    for (Node node = leaf; node != null; node = node.parent) {
      path.add(node);
    }

    leaf.parent = null;
    for (int i = 0; i < path.size() - 1; ++i) {
      Node current = path.get(i);
      Node originalParent = path.get(i + 1);

      if (current.left != null) {
        current.right = current.left;
      }

      current.left = originalParent;
      originalParent.parent = current;
      if (originalParent.left == current) {
        originalParent.left = null;
      } else {
        originalParent.right = null;
      }
    }

    return leaf;
  }
}
