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
  int maxDepth;
  int leftValueWithMaxDepth;

  public int findBottomLeftValue(TreeNode root) {
    maxDepth = -1;
    search(root, 0);

    return leftValueWithMaxDepth;
  }

  void search(TreeNode node, int depth) {
    if (depth > maxDepth) {
      maxDepth = depth;
      leftValueWithMaxDepth = node.val;
    }

    if (node.left != null) {
      search(node.left, depth + 1);
    }
    if (node.right != null) {
      search(node.right, depth + 1);
    }
  }
}
