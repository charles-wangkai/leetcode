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
    return findDepth(root) >= 0;
  }

  int findDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftDepth = findDepth(node.left);
    int rightDepth = findDepth(node.right);

    return (leftDepth >= 0 && rightDepth >= 0 && Math.abs(leftDepth - rightDepth) <= 1)
        ? (1 + Math.max(leftDepth, rightDepth))
        : -1;
  }
}
