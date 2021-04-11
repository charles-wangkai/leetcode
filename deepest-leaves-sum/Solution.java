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
  int maxDepth;
  int sumWithMaxDepth;

  public int deepestLeavesSum(TreeNode root) {
    maxDepth = -1;
    sumWithMaxDepth = 0;

    search(root, 0);

    return sumWithMaxDepth;
  }

  void search(TreeNode node, int depth) {
    if (node == null) {
      return;
    }

    if (depth > maxDepth) {
      maxDepth = depth;
      sumWithMaxDepth = node.val;
    } else if (depth == maxDepth) {
      sumWithMaxDepth += node.val;
    }

    search(node.left, depth + 1);
    search(node.right, depth + 1);
  }
}
