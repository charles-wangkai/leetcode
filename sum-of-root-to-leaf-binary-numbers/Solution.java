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
  public int sumRootToLeaf(TreeNode root) {
    return search(root, 0);
  }

  int search(TreeNode node, int parentValue) {
    if (node == null) {
      return 0;
    }

    int currentValue = parentValue * 2 + node.val;

    if (node.left == null && node.right == null) {
      return currentValue;
    }

    return search(node.left, currentValue) + search(node.right, currentValue);
  }
}
