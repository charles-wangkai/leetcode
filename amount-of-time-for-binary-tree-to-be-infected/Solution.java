import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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
    search(valueToNode, valueToParent, null, root);

    Map<Integer, Integer> valueToDistance = new HashMap<>();
    valueToDistance.put(start, 0);
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
      int head = queue.poll();

      for (TreeNode next :
          new TreeNode[] {
            valueToParent.get(head), valueToNode.get(head).left, valueToNode.get(head).right
          }) {
        addNext(valueToDistance, queue, next, valueToDistance.get(head) + 1);
      }
    }

    return valueToDistance.values().stream().mapToInt(x -> x).max().getAsInt();
  }

  void addNext(
      Map<Integer, Integer> valueToDistance, Queue<Integer> queue, TreeNode next, int distance) {
    if (next != null && !valueToDistance.containsKey(next.val)) {
      valueToDistance.put(next.val, distance);
      queue.offer(next.val);
    }
  }

  void search(
      Map<Integer, TreeNode> valueToNode,
      Map<Integer, TreeNode> valueToParent,
      TreeNode parent,
      TreeNode node) {
    if (node != null) {
      valueToNode.put(node.val, node);
      valueToParent.put(node.val, parent);

      search(valueToNode, valueToParent, node, node.left);
      search(valueToNode, valueToParent, node, node.right);
    }
  }
}