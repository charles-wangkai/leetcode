import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
  int nodeCount;
  int maxValueSum;

  public int maxSum(TreeNode root) {
    nodeCount = 0;
    Map<Integer, Integer> nodeToValue = new HashMap<>();
    Map<Integer, List<Integer>> nodeToAdjs = new HashMap<>();
    search1(nodeToValue, nodeToAdjs, -1, root);

    maxValueSum = Integer.MIN_VALUE;
    for (int node : nodeToValue.keySet()) {
      search2(nodeToValue, nodeToAdjs, new HashSet<>(), 0, node);
    }

    return maxValueSum;
  }

  void search2(
      Map<Integer, Integer> nodeToValue,
      Map<Integer, List<Integer>> nodeToAdjs,
      Set<Integer> seen,
      int valueSum,
      int node) {
    int value = nodeToValue.get(node);

    seen.add(value);
    valueSum += value;

    maxValueSum = Math.max(maxValueSum, valueSum);

    for (int adj : nodeToAdjs.get(node)) {
      if (!seen.contains(nodeToValue.get(adj))) {
        search2(nodeToValue, nodeToAdjs, seen, valueSum, adj);
      }
    }

    seen.remove(value);
    valueSum -= value;
  }

  void search1(
      Map<Integer, Integer> nodeToValue,
      Map<Integer, List<Integer>> nodeToAdjs,
      int parent,
      TreeNode treeNode) {
    int node = nodeCount;
    ++nodeCount;

    nodeToValue.put(node, treeNode.val);

    nodeToAdjs.put(node, new ArrayList<>());
    if (parent != -1) {
      nodeToAdjs.get(parent).add(node);
      nodeToAdjs.get(node).add(parent);
    }

    if (treeNode.left != null) {
      search1(nodeToValue, nodeToAdjs, node, treeNode.left);
    }
    if (treeNode.right != null) {
      search1(nodeToValue, nodeToAdjs, node, treeNode.right);
    }
  }
}