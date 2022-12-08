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

class Solution {
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> leafValues1 = new ArrayList<>();
    search(leafValues1, root1);

    List<Integer> leafValues2 = new ArrayList<>();
    search(leafValues2, root2);

    return leafValues1.equals(leafValues2);
  }

  void search(List<Integer> leafValues, TreeNode node) {
    if (node.left != null) {
      search(leafValues, node.left);
    }

    if (node.left == null && node.right == null) {
      leafValues.add(node.val);
    }

    if (node.right != null) {
      search(leafValues, node.right);
    }
  }
}
