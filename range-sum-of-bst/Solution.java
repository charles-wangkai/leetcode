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
  public int rangeSumBST(TreeNode root, int low, int high) {
    return search(low, high, root);
  }

  int search(int low, int high, TreeNode node) {
    return (node == null)
        ? 0
        : (((node.val >= low && node.val <= high) ? node.val : 0)
            + search(low, high, node.left)
            + search(low, high, node.right));
  }
}
