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
  public int maxAncestorDiff(TreeNode root) {
    return search(root, root.val, root.val);
  }

  int search(TreeNode node, int minAncestor, int maxAncestor) {
    if (node == null) {
      return -1;
    }

    int nextMinAncestor = Math.min(minAncestor, node.val);
    int nextMaxAncestor = Math.max(maxAncestor, node.val);

    return Math.max(
        nextMaxAncestor - nextMinAncestor,
        Math.max(
            search(node.left, nextMinAncestor, nextMaxAncestor),
            search(node.right, nextMinAncestor, nextMaxAncestor)));
  }
}
