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
  int maxLength;

  public int longestZigZag(TreeNode root) {
    maxLength = 0;
    search(root);

    return maxLength;
  }

  Outcome search(TreeNode node) {
    int leftLength = (node.left == null) ? 0 : (1 + search(node.left).rightLength());
    int rightLength = (node.right == null) ? 0 : (1 + search(node.right).leftLength());
    maxLength = Math.max(maxLength, Math.max(leftLength, rightLength));

    return new Outcome(leftLength, rightLength);
  }
}

record Outcome(int leftLength, int rightLength) {}
