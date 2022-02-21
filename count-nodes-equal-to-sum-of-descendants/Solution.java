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
  int sameSumCount;

  public int equalToDescendants(TreeNode root) {
    sameSumCount = 0;
    search(root);

    return sameSumCount;
  }

  long search(TreeNode node) {
    if (node == null) {
      return 0;
    }

    long sum = search(node.left) + search(node.right);
    if (sum == node.val) {
      ++sameSumCount;
    }

    return sum + node.val;
  }
}