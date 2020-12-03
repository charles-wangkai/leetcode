import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

public class Solution {
  public TreeNode increasingBST(TreeNode root) {
    List<TreeNode> nodes = new ArrayList<>();
    inorderSearch(nodes, root);

    for (int i = 0; i < nodes.size(); ++i) {
      TreeNode node = nodes.get(i);

      node.left = null;
      node.right = (i + 1 == nodes.size()) ? null : nodes.get(i + 1);
    }

    return nodes.get(0);
  }

  void inorderSearch(List<TreeNode> nodes, TreeNode node) {
    if (node == null) {
      return;
    }

    inorderSearch(nodes, node.left);
    nodes.add(node);
    inorderSearch(nodes, node.right);
  }
}
