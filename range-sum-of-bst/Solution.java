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
    return search(root, Integer.MIN_VALUE, Integer.MAX_VALUE, low, high);
  }

  int search(TreeNode node, int lower, int upper, int low, int high) {
    if (node == null || upper < low || lower > high) {
      return 0;
    }

    int result = 0;
    if (node.val >= low && node.val <= high) {
      result += node.val;
    }
    result += search(node.left, lower, node.val - 1, low, high);
    result += search(node.right, node.val + 1, upper, low, high);

    return result;
  }
}
