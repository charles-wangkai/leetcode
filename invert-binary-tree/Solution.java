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
  public TreeNode invertTree(TreeNode root) {
    return (root == null)
        ? null
        : new TreeNode(root.val, invertTree(root.right), invertTree(root.left));
  }
}
