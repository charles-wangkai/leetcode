import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
  public int kthLargestPerfectSubtree(TreeNode root, int k) {
    List<Integer> perfectSizes = new ArrayList<>();
    search(perfectSizes, root);
    Collections.sort(perfectSizes, Comparator.reverseOrder());

    return (perfectSizes.size() >= k) ? perfectSizes.get(k - 1) : -1;
  }

  int search(List<Integer> perfectSizes, TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftDepth = search(perfectSizes, node.left);
    int rightDepth = search(perfectSizes, node.right);
    if (leftDepth == -1 || rightDepth == -1 || leftDepth != rightDepth) {
      return -1;
    }

    perfectSizes.add((1 << (leftDepth + 1)) - 1);

    return leftDepth + 1;
  }
}