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
  public String tree2str(TreeNode root) {
    if (root.left == null) {
      if (root.right == null) {
        return String.valueOf(root.val);
      }

      return String.format("%d()(%s)", root.val, tree2str(root.right));
    }

    if (root.right == null) {
      return String.format("%d(%s)", root.val, tree2str(root.left));
    }

    return String.format("%d(%s)(%s)", root.val, tree2str(root.left), tree2str(root.right));
  }
}
