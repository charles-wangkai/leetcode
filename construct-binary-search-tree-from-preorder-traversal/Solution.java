import java.util.Arrays;

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
  public TreeNode bstFromPreorder(int[] preorder) {
    if (preorder.length == 0) {
      return null;
    }

    int endIndex = 1;
    while (endIndex != preorder.length && preorder[endIndex] < preorder[0]) {
      ++endIndex;
    }

    return new TreeNode(
        preorder[0],
        bstFromPreorder(Arrays.copyOfRange(preorder, 1, endIndex)),
        bstFromPreorder(Arrays.copyOfRange(preorder, endIndex, preorder.length)));
  }
}
