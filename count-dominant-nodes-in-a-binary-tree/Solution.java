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
  public int countDominantNodes(TreeNode root) {
    return search(root).dominantCount();
  }

  Outcome search(TreeNode node) {
    if (node == null) {
      return new Outcome(0, Integer.MIN_VALUE);
    }

    Outcome leftOutcome = search(node.left);
    Outcome rightOutcome = search(node.right);

    int dominantCount = leftOutcome.dominantCount() + rightOutcome.dominantCount();
    int maxValue = Math.max(leftOutcome.maxValue(), rightOutcome.maxValue());

    if (node.val >= maxValue) {
      ++dominantCount;
      maxValue = node.val;
    }

    return new Outcome(dominantCount, maxValue);
  }
}

record Outcome(int dominantCount, int maxValue) {}
