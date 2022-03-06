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
    Map<Integer, List<int[]>> parentToDescriptions = new HashMap<>();
    for (int[] description : descriptions) {
      if (!parentToDescriptions.containsKey(description[0])) {
        parentToDescriptions.put(description[0], new ArrayList<>());
      }
      parentToDescriptions.get(description[0]).add(description);
    }

    Set<Integer> children =
        Arrays.stream(descriptions).map(description -> description[1]).collect(Collectors.toSet());

    int root =
        parentToDescriptions.keySet().stream()
            .filter(parent -> !children.contains(parent))
            .findAny()
            .get();

    return buildNode(parentToDescriptions, root);
  }

  TreeNode buildNode(Map<Integer, List<int[]>> parentToDescriptions, int value) {
    TreeNode node = new TreeNode(value);
    for (int[] description : parentToDescriptions.getOrDefault(value, List.of())) {
      if (description[2] == 1) {
        node.left = buildNode(parentToDescriptions, description[1]);
      } else {
        node.right = buildNode(parentToDescriptions, description[1]);
      }
    }

    return node;
  }
}