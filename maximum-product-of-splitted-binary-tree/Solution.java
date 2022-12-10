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
  static final int MODULUS = 1_000_000_007;

  int total;
  long productMax;

  public int maxProduct(TreeNode root) {
    total = -1;
    total = search(root);
    productMax = -1;
    search(root);

    return (int) (productMax % MODULUS);
  }

  int search(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int sum = node.val + search(node.left) + search(node.right);

    if (total != -1) {
      productMax = Math.max(productMax, (long) sum * (total - sum));
    }

    return sum;
  }
}
