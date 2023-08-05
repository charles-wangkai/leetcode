import java.util.ArrayList;
import java.util.Arrays;
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
  public List<TreeNode> generateTrees(int n) {
    return buildTrees(1, n);
  }

  List<TreeNode> buildTrees(int beginValue, int endValue) {
    if (beginValue > endValue) {
      return Arrays.asList((TreeNode) null);
    }

    List<TreeNode> roots = new ArrayList<>();
    for (int i = beginValue; i <= endValue; ++i) {
      List<TreeNode> lefts = buildTrees(beginValue, i - 1);
      List<TreeNode> rights = buildTrees(i + 1, endValue);
      for (TreeNode left : lefts) {
        for (TreeNode right : rights) {
          roots.add(new TreeNode(i, left, right));
        }
      }
    }

    return roots;
  }
}
