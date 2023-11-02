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
  public int averageOfSubtree(TreeNode root) {
    return search(root).sameValueAsAvgNum();
  }

  Outcome search(TreeNode node) {
    if (node == null) {
      return new Outcome(0, 0, 0);
    }

    Outcome leftOutcome = search(node.left);
    Outcome rightOutcome = search(node.right);

    int sum = node.val + leftOutcome.sum() + rightOutcome.sum();
    int count = 1 + leftOutcome.count() + rightOutcome.count();
    int sameValueAsAvgNum =
        ((node.val == sum / count) ? 1 : 0)
            + leftOutcome.sameValueAsAvgNum()
            + rightOutcome.sameValueAsAvgNum();

    return new Outcome(sum, count, sameValueAsAvgNum);
  }
}

record Outcome(int sum, int count, int sameValueAsAvgNum) {}
