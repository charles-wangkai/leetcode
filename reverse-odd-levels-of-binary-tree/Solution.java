import java.util.List;
import java.util.stream.Stream;

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
  public TreeNode reverseOddLevels(TreeNode root) {
    List<TreeNode> level = List.of(root);
    for (int i = 0; level.get(0) != null; ++i) {
      if (i % 2 == 1) {
        for (int begin = 0, end = level.size() - 1; begin < end; ++begin, --end) {
          int temp = level.get(begin).val;
          level.get(begin).val = level.get(end).val;
          level.get(end).val = temp;
        }
      }

      level = level.stream().flatMap(node -> Stream.of(node.left, node.right)).toList();
    }

    return root;
  }
}