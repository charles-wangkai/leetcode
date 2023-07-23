import java.util.ArrayList;
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
  public List<TreeNode> allPossibleFBT(int n) {
    if (n % 2 == 0) {
      return List.of();
    }

    if (n == 1) {
      return List.of(new TreeNode());
    }

    List<TreeNode> result = new ArrayList<>();
    for (int left = 1; left < n - 1; left += 2) {
      int right = n - 1 - left;

      List<TreeNode> leftSubTrees = allPossibleFBT(left);
      List<TreeNode> rightSubTrees = allPossibleFBT(right);
      for (TreeNode leftSubTree : leftSubTrees) {
        for (TreeNode rightSubTree : rightSubTrees) {
          result.add(new TreeNode(0, leftSubTree, rightSubTree));
        }
      }
    }

    return result;
  }
}
