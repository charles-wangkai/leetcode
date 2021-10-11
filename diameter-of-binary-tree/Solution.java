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
  int diameter;

  public int diameterOfBinaryTree(TreeNode root) {
    diameter = -1;
    search(root);

    return diameter;
  }

  int search(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftHeight = search(node.left);
    int rightHeight = search(node.right);

    diameter = Math.max(diameter, leftHeight + rightHeight);

    return Math.max(leftHeight, rightHeight) + 1;
  }
}
