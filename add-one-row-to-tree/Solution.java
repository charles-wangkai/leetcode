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
  public TreeNode addOneRow(TreeNode root, int v, int d) {
    if (d == 1) {
      return new TreeNode(v, root, null);
    } else {
      insert(root, v, d, 2);

      return root;
    }
  }

  void insert(TreeNode parent, int v, int d, int depth) {
    if (depth == d) {
      parent.left = new TreeNode(v, parent.left, null);
      parent.right = new TreeNode(v, null, parent.right);

      return;
    }

    if (parent.left != null) {
      insert(parent.left, v, d, depth + 1);
    }
    if (parent.right != null) {
      insert(parent.right, v, d, depth + 1);
    }
  }
}
