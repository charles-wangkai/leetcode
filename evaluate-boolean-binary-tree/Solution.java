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
  public boolean evaluateTree(TreeNode root) {
    if (root.val <= 1) {
      return root.val == 1;
    }

    boolean leftSubResult = evaluateTree(root.left);
    boolean rightSubResult = evaluateTree(root.right);

    return (root.val == 2) ? (leftSubResult || rightSubResult) : (leftSubResult && rightSubResult);
  }
}