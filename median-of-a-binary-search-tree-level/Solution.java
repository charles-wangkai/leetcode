import java.util.ArrayList;
import java.util.List;

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
  public int levelMedian(TreeNode root, int level) {
    List<Integer> values = new ArrayList<>();
    search(values, level, root, 0);

    return values.isEmpty() ? -1 : values.get(values.size() / 2);
  }

  void search(List<Integer> values, int level, TreeNode node, int currentLevel) {
    if (currentLevel == level) {
      values.add(node.val);
    }

    if (node.left != null) {
      search(values, level, node.left, currentLevel + 1);
    }
    if (node.right != null) {
      search(values, level, node.right, currentLevel + 1);
    }
  }
}