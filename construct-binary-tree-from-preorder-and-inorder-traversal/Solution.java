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
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  TreeNode buildTree(
      int[] preorder, int beginPre, int endPre, int[] inorder, int beginIn, int endIn) {
    if (beginPre > endPre) {
      return null;
    }

    int rootValue = preorder[beginPre];
    int rootIn = findValue(inorder, beginIn, rootValue);
    int leftLength = rootIn - beginIn;
    int rightLength = endIn - rootIn;

    return new TreeNode(
        rootValue,
        buildTree(preorder, beginPre + 1, beginPre + leftLength, inorder, beginIn, rootIn - 1),
        buildTree(preorder, endPre - rightLength + 1, endPre, inorder, rootIn + 1, endIn));
  }

  int findValue(int[] a, int begin, int target) {
    for (int i = begin; ; ++i) {
      if (a[i] == target) {
        return i;
      }
    }
  }
}
