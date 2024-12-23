import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
  public int minimumOperations(TreeNode root) {
    List<List<Integer>> levels = new ArrayList<>();
    buildLevels(levels, 0, root);

    return levels.stream().mapToInt(this::computeOperationNum).sum();
  }

  void buildLevels(List<List<Integer>> levels, int index, TreeNode node) {
    if (node == null) {
      return;
    }

    if (index == levels.size()) {
      levels.add(new ArrayList<>());
    }
    levels.get(index).add(node.val);

    buildLevels(levels, index + 1, node.left);
    buildLevels(levels, index + 1, node.right);
  }

  int computeOperationNum(List<Integer> level) {
    int[] sorted = level.stream().sorted().mapToInt(Integer::intValue).toArray();
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, sorted.length).boxed().collect(Collectors.toMap(i -> sorted[i], i -> i));

    int result = 0;
    boolean[] visited = new boolean[level.size()];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        result += search(level, visited, valueToIndex, i) - 1;
      }
    }

    return result;
  }

  int search(
      List<Integer> level, boolean[] visited, Map<Integer, Integer> valueToIndex, int index) {
    if (visited[index]) {
      return 0;
    }

    visited[index] = true;

    return 1 + search(level, visited, valueToIndex, valueToIndex.get(level.get(index)));
  }
}
