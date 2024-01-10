import java.util.HashMap;
import java.util.Map;

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
  public int amountOfTime(TreeNode root, int start) {
    Map<Integer, TreeNode> valueToNode = new HashMap<>();
    Map<Integer, TreeNode> valueToParent = new HashMap<>();
    search1(valueToNode, valueToParent, null, root);

    Map<Integer, Integer> valueToDistance = new HashMap<>();
    search2(valueToDistance, valueToParent, 0, valueToNode.get(start));

    return valueToDistance.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }

  void search2(
      Map<Integer, Integer> valueToDistance,
      Map<Integer, TreeNode> valueToParent,
      int distance,
      TreeNode node) {
    valueToDistance.put(node.val, distance);

    for (TreeNode adj : new TreeNode[] {valueToParent.get(node.val), node.left, node.right}) {
      if (adj != null && !valueToDistance.containsKey(adj.val)) {
        search2(valueToDistance, valueToParent, distance + 1, adj);
      }
    }
  }

  void search1(
      Map<Integer, TreeNode> valueToNode,
      Map<Integer, TreeNode> valueToParent,
      TreeNode parent,
      TreeNode node) {
    if (node != null) {
      valueToNode.put(node.val, node);
      valueToParent.put(node.val, parent);

      search1(valueToNode, valueToParent, node, node.left);
      search1(valueToNode, valueToParent, node, node.right);
    }
  }
}