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
  public List<Double> averageOfLevels(TreeNode root) {
    List<List<Integer>> values = new ArrayList<>();
    search(values, 0, root);

    return values.stream()
        .map(line -> line.stream().mapToInt(x -> x).average().getAsDouble())
        .toList();
  }

  void search(List<List<Integer>> values, int index, TreeNode node) {
    if (node == null) {
      return;
    }

    if (index == values.size()) {
      values.add(new ArrayList<>());
    }
    values.get(index).add(node.val);

    search(values, index + 1, node.left);
    search(values, index + 1, node.right);
  }
}
