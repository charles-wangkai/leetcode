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
  public int sumNumbers(TreeNode root) {
    return search(root, 0);
  }

  int search(TreeNode node, int prev) {
    int current = prev * 10 + node.val;

    return (node.left == null && node.right == null)
        ? current
        : (((node.left == null) ? 0 : search(node.left, current))
            + ((node.right == null) ? 0 : search(node.right, current)));
  }
}
