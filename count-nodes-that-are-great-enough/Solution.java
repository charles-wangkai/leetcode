import java.util.Comparator;
import java.util.PriorityQueue;

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
  public int countGreatEnoughNodes(TreeNode root, int k) {
    return search(k, root).greatEnoughCount();
  }

  Outcome search(int k, TreeNode node) {
    int greatEnoughCount = 0;

    PriorityQueue<Integer> minValues = new PriorityQueue<>(Comparator.reverseOrder());
    addValue(minValues, k, node.val);

    if (node.left != null) {
      Outcome leftOutcome = search(k, node.left);
      greatEnoughCount += leftOutcome.greatEnoughCount();
      for (int leftMinValue : leftOutcome.minValues()) {
        addValue(minValues, k, leftMinValue);
      }
    }
    if (node.right != null) {
      Outcome rightOutcome = search(k, node.right);
      greatEnoughCount += rightOutcome.greatEnoughCount();
      for (int rightMinValue : rightOutcome.minValues()) {
        addValue(minValues, k, rightMinValue);
      }
    }

    if (minValues.size() == k && node.val > minValues.peek()) {
      ++greatEnoughCount;
    }

    return new Outcome(greatEnoughCount, minValues);
  }

  void addValue(PriorityQueue<Integer> minValues, int k, int value) {
    minValues.offer(value);
    if (minValues.size() == k + 1) {
      minValues.poll();
    }
  }
}

record Outcome(int greatEnoughCount, PriorityQueue<Integer> minValues) {}
