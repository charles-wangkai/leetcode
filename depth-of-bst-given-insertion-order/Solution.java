import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int maxDepthBST(int[] order) {
    Node root = new Node(1, order.length);

    NavigableMap<Integer, Node> valueToEmptyLeftNodes = new TreeMap<>();
    valueToEmptyLeftNodes.put(order[0], root);
    NavigableMap<Integer, Node> valueToEmptyRightNodes = new TreeMap<>();
    valueToEmptyRightNodes.put(order[0], root);

    for (int i = 1; i < order.length; ++i) {
      Integer lower = valueToEmptyRightNodes.lowerKey(order[i]);
      if (lower != null && order[i] <= valueToEmptyRightNodes.get(lower).max) {
        insert(valueToEmptyLeftNodes, valueToEmptyRightNodes, lower, order[i]);
      } else {
        insert(
            valueToEmptyLeftNodes,
            valueToEmptyRightNodes,
            valueToEmptyLeftNodes.higherKey(order[i]),
            order[i]);
      }
    }

    return computeMaxDepth(root);
  }

  void insert(
      NavigableMap<Integer, Node> valueToEmptyLeftNodes,
      NavigableMap<Integer, Node> valueToEmptyRightNodes,
      int value,
      int toAdd) {
    Node node;
    if (toAdd < value) {
      Node parent = valueToEmptyLeftNodes.get(value);
      node = new Node(parent.min, value - 1);
      parent.left = node;
      valueToEmptyLeftNodes.remove(value);
    } else {
      Node parent = valueToEmptyRightNodes.get(value);
      node = new Node(value + 1, parent.max);
      parent.right = node;
      valueToEmptyRightNodes.remove(value);
    }

    valueToEmptyLeftNodes.put(toAdd, node);
    valueToEmptyRightNodes.put(toAdd, node);
  }

  int computeMaxDepth(Node node) {
    return (node == null)
        ? 0
        : (1 + Math.max(computeMaxDepth(node.left), computeMaxDepth(node.right)));
  }
}

class Node {
  int min;
  int max;
  Node left;
  Node right;

  Node(int min, int max) {
    this.min = min;
    this.max = max;
  }
}