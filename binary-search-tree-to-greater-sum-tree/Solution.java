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
  int sum;

  public TreeNode bstToGst(TreeNode root) {
    sum = 0;
    search(root);

    return root;
  }

  void search(TreeNode node) {
    if (node != null) {
      search(node.right);

      sum += node.val;
      node.val = sum;

      search(node.left);
    }
  }
}