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
  int maxSum;

  public int maxPathSum(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    search(root);

    return maxSum;
  }

  int search(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftMaxPathSum = search(node.left);
    int rightMaxPathSum = search(node.right);
    maxSum = Math.max(maxSum, node.val + leftMaxPathSum + rightMaxPathSum);

    return Math.max(0, node.val + Math.max(leftMaxPathSum, rightMaxPathSum));
  }
}
