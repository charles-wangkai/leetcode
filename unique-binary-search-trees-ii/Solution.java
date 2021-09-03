import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
    return buildTrees(IntStream.rangeClosed(1, n).toArray(), 0, n - 1);
  }

  List<TreeNode> buildTrees(int[] values, int begin, int end) {
    if (begin > end) {
      return Collections.singletonList(null);
    }

    List<TreeNode> roots = new ArrayList<>();
    for (int i = begin; i <= end; ++i) {
      List<TreeNode> lefts = buildTrees(values, begin, i - 1);
      List<TreeNode> rights = buildTrees(values, i + 1, end);
      for (TreeNode left : lefts) {
        for (TreeNode right : rights) {
          roots.add(new TreeNode(values[i], left, right));
        }
      }
    }

    return roots;
  }
}
