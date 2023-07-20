// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (isSame(s, t)) {
      return true;
    }

    if (s != null) {
      if (isSubtree(s.left, t)) {
        return true;
      }
      if (isSubtree(s.right, t)) {
        return true;
      }
    }
    return false;
  }

  boolean isSame(TreeNode s, TreeNode t) {
    if (s == null) {
      return t == null;
    } else {
      if (t == null) {
        return false;
      } else {
        return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
      }
    }
  }
}
