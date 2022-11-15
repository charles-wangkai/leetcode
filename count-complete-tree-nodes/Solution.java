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
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int height = findHeight(root);

    int result = -1;
    int lower = 1 << (height - 1);
    int upper = (1 << height) - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(root, height, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int findHeight(TreeNode root) {
    int result = 0;
    for (TreeNode node = root; node != null; node = node.left) {
      ++result;
    }

    return result;
  }

  boolean check(TreeNode root, int height, int value) {
    TreeNode node = root;
    for (int i = 0; i < height - 1; ++i) {
      if (((value >> (height - 2 - i)) & 1) == 0) {
        node = node.left;
      } else {
        node = node.right;
      }

      if (node == null) {
        return false;
      }
    }

    return true;
  }
}
