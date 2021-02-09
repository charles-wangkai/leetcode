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
  int greaterSum;

  public TreeNode convertBST(TreeNode root) {
    greaterSum = 0;
    search(root);

    return root;
  }

  void search(TreeNode node) {
    if (node == null) {
      return;
    }

    search(node.right);

    node.val += greaterSum;
    greaterSum = node.val;

    search(node.left);
  }
}
