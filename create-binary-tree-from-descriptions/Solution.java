import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
  public TreeNode createBinaryTree(int[][] descriptions) {
    Map<Integer, List<Integer>> parentToDescriptions = new HashMap<>();
    for (int i = 0; i < descriptions.length; ++i) {
      parentToDescriptions.putIfAbsent(descriptions[i][0], new ArrayList<>());
      parentToDescriptions.get(descriptions[i][0]).add(i);
    }

    Set<Integer> children =
        Arrays.stream(descriptions).map(description -> description[1]).collect(Collectors.toSet());
    int root =
        parentToDescriptions.keySet().stream()
            .filter(parent -> !children.contains(parent))
            .findAny()
            .get();

    return buildNode(descriptions, parentToDescriptions, root);
  }

  TreeNode buildNode(
      int[][] descriptions, Map<Integer, List<Integer>> parentToDescriptions, int value) {
    TreeNode node = new TreeNode(value);
    for (int d : parentToDescriptions.getOrDefault(value, List.of())) {
      if (descriptions[d][2] == 1) {
        node.left = buildNode(descriptions, parentToDescriptions, descriptions[d][1]);
      } else {
        node.right = buildNode(descriptions, parentToDescriptions, descriptions[d][1]);
      }
    }

    return node;
  }
}