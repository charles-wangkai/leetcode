import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    search(root, values, 0);

    return values.stream()
        .map(line -> line.stream().mapToInt(x -> x).average().getAsDouble())
        .collect(Collectors.toList());
  }

  void search(TreeNode node, List<List<Integer>> values, int index) {
    if (node == null) {
      return;
    }

    if (index == values.size()) {
      values.add(new ArrayList<>());
    }
    values.get(index).add(node.val);

    search(node.left, values, index + 1);
    search(node.right, values, index + 1);
  }
}
