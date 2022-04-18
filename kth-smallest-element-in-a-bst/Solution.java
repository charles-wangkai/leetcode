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
  int kthMin;
  int sequence;

  public int kthSmallest(TreeNode root, int k) {
    sequence = 0;
    search(root, k);

    return kthMin;
  }

  void search(TreeNode node, int k) {
    if (node == null || sequence == k) {
      return;
    }

    search(node.left, k);

    ++sequence;
    if (sequence == k) {
      kthMin = node.val;
    }

    search(node.right, k);
  }
}
