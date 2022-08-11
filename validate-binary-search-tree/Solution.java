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
  public boolean isValidBST(TreeNode root) {
    return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  boolean check(TreeNode root, long lower, long upper) {
    return root == null
        || (root.val >= lower
            && root.val <= upper
            && check(root.left, lower, root.val - 1L)
            && check(root.right, root.val + 1L, upper));
  }
}
