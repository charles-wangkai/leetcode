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
  public boolean isBalanced(TreeNode root) {
    return computeDepth(root) != -1;
  }

  int computeDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftDepth = computeDepth(node.left);
    int rightDepth = computeDepth(node.right);

    return (leftDepth != -1 && rightDepth != -1 && Math.abs(leftDepth - rightDepth) <= 1)
        ? (1 + Math.max(leftDepth, rightDepth))
        : -1;
  }
}
