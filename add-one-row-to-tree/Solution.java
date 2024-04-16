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
  public TreeNode addOneRow(TreeNode root, int val, int depth) {
    if (depth == 1) {
      return new TreeNode(val, root, null);
    }

    insert(val, depth, root, 2);

    return root;
  }

  void insert(int val, int depth, TreeNode parent, int d) {
    if (d == depth) {
      parent.left = new TreeNode(val, parent.left, null);
      parent.right = new TreeNode(val, null, parent.right);
    } else {
      if (parent.left != null) {
        insert(val, depth, parent.left, d + 1);
      }
      if (parent.right != null) {
        insert(val, depth, parent.right, d + 1);
      }
    }
  }
}
