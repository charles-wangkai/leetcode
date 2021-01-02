// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Solution {
  public final TreeNode getTargetCopy(
      final TreeNode original, final TreeNode cloned, final TreeNode target) {
    if (original == null) {
      return null;
    }
    if (original == target) {
      return cloned;
    }

    TreeNode subLeftResult = getTargetCopy(original.left, cloned.left, target);
    if (subLeftResult != null) {
      return subLeftResult;
    }

    return getTargetCopy(original.right, cloned.right, target);
  }
}
