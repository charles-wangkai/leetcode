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
  int moveNum;

  public int distributeCoins(TreeNode root) {
    moveNum = 0;
    search(root);

    return moveNum;
  }

  int search(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int result = (node.val - 1) + search(node.left) + search(node.right);
    moveNum += Math.abs(result);

    return result;
  }
}
