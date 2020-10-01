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
  int uDepth;
  TreeNode nearestRight;

  public TreeNode findNeartestRightNode(TreeNode root, TreeNode u) {
    uDepth = -1;
    nearestRight = null;
    search(u, root, 0);

    return nearestRight;
  }

  void search(TreeNode u, TreeNode node, int depth) {
    if (depth == uDepth && nearestRight == null) {
      nearestRight = node;
    }
    if (node == u) {
      uDepth = depth;
    }

    if (node.left != null) {
      search(u, node.left, depth + 1);
    }
    if (node.right != null) {
      search(u, node.right, depth + 1);
    }
  }
}
