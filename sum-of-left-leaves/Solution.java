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
  public int sumOfLeftLeaves(TreeNode root) {
    return search(root, false);
  }

  int search(TreeNode node, boolean left) {
    return (node.left == null && node.right == null && left)
        ? node.val
        : (((node.left == null) ? 0 : search(node.left, true))
            + ((node.right == null) ? 0 : search(node.right, false)));
  }
}
