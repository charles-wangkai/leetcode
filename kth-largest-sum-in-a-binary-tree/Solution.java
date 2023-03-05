import java.util.ArrayList;
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
  public long kthLargestLevelSum(TreeNode root, int k) {
    List<Long> levelSums = new ArrayList<>();
    search(levelSums, 0, root);

    return levelSums.stream().sorted(Comparator.reverseOrder()).skip(k - 1).findFirst().orElse(-1L);
  }

  void search(List<Long> levelSums, int level, TreeNode node) {
    if (node != null) {
      if (level == levelSums.size()) {
        levelSums.add(0L);
      }
      levelSums.set(level, levelSums.get(level) + node.val);

      search(levelSums, level + 1, node.left);
      search(levelSums, level + 1, node.right);
    }
  }
}
