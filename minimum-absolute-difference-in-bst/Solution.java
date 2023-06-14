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
  Integer prevValue;
  int minDiff;

  public int getMinimumDifference(TreeNode root) {
    prevValue = null;
    minDiff = Integer.MAX_VALUE;
    search(root);

    return minDiff;
  }

  void search(TreeNode node) {
    if (node == null) {
      return;
    }

    search(node.left);

    if (prevValue != null) {
      minDiff = Math.min(minDiff, node.val - prevValue);
    }
    prevValue = node.val;

    search(node.right);
  }
}
