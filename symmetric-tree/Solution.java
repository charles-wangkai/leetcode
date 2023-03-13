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
  public boolean isSymmetric(TreeNode root) {
    return isSame(root, root);
  }

  boolean isSame(TreeNode node1, TreeNode node2) {
    return (node1 == null && node2 == null)
        || (node1 != null
            && node2 != null
            && node1.val == node2.val
            && isSame(node1.left, node2.right)
            && isSame(node1.right, node2.left));
  }
}
