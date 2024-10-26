import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
  public int[] treeQueries(TreeNode root, int[] queries) {
    Map<Integer, TreeNode> valueToNode = new HashMap<>();
    Map<Integer, TreeNode> valueToParent = new HashMap<>();
    Map<Integer, Integer> valueToHeight = new HashMap<>();
    Map<Integer, Integer> valueToSubtreeHeight = new HashMap<>();
    search(valueToNode, valueToParent, valueToHeight, valueToSubtreeHeight, null, 0, root);

    SortedMap<Integer, Integer> heightToCount = new TreeMap<>();
    for (int value : valueToNode.keySet()) {
      if (valueToNode.get(value).left == null && valueToNode.get(value).right == null) {
        updateMap(heightToCount, valueToSubtreeHeight.get(value), 1);
      }
    }

    Map<Integer, Integer> valueToRemovalHeight = new HashMap<>();
    int[] sortedValues =
        valueToHeight.keySet().stream()
            .sorted(Comparator.comparing(valueToHeight::get).reversed())
            .mapToInt(Integer::intValue)
            .toArray();
    for (int value : sortedValues) {
      TreeNode node = valueToNode.get(value);
      if (node.left != null) {
        updateMap(heightToCount, valueToSubtreeHeight.get(node.left.val), -1);
      }
      if (node.right != null) {
        updateMap(heightToCount, valueToSubtreeHeight.get(node.right.val), -1);
      }
      if (node.left == null && node.right == null) {
        updateMap(heightToCount, valueToSubtreeHeight.get(value), -1);
      }
      updateMap(heightToCount, valueToHeight.get(value) - 1, 1);

      valueToRemovalHeight.put(value, heightToCount.lastKey());

      updateMap(heightToCount, valueToHeight.get(value) - 1, -1);
      updateMap(heightToCount, valueToSubtreeHeight.get(value), 1);
    }

    return Arrays.stream(queries).map(valueToRemovalHeight::get).toArray();
  }

  void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }

  void search(
      Map<Integer, TreeNode> valueToNode,
      Map<Integer, TreeNode> valueToParent,
      Map<Integer, Integer> valueToHeight,
      Map<Integer, Integer> valueToSubtreeHeight,
      TreeNode parent,
      int height,
      TreeNode node) {
    valueToNode.put(node.val, node);
    valueToParent.put(node.val, parent);
    valueToHeight.put(node.val, height);
    if (node.left != null) {
      search(
          valueToNode,
          valueToParent,
          valueToHeight,
          valueToSubtreeHeight,
          node,
          height + 1,
          node.left);
    }
    if (node.right != null) {
      search(
          valueToNode,
          valueToParent,
          valueToHeight,
          valueToSubtreeHeight,
          node,
          height + 1,
          node.right);
    }

    int subtreeHeight = height;
    if (node.left != null) {
      subtreeHeight = Math.max(subtreeHeight, valueToSubtreeHeight.get(node.left.val));
    }
    if (node.right != null) {
      subtreeHeight = Math.max(subtreeHeight, valueToSubtreeHeight.get(node.right.val));
    }
    valueToSubtreeHeight.put(node.val, subtreeHeight);
  }
}
