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
  public int minimumFlips(TreeNode root, boolean result) {
    Outcome outcome = search(root);

    return result ? outcome.trueCost() : outcome.falseCost();
  }

  Outcome search(TreeNode node) {
    if (node == null) {
      return null;
    }

    Outcome leftOutcome = search(node.left);
    Outcome rightOutcome = search(node.right);

    if (node.val == 0) {
      return new Outcome(0, 1);
    }
    if (node.val == 1) {
      return new Outcome(1, 0);
    }
    if (node.val == 2) {
      return new Outcome(
          leftOutcome.falseCost() + rightOutcome.falseCost(),
          Math.min(
              Math.min(
                  leftOutcome.falseCost() + rightOutcome.trueCost(),
                  leftOutcome.trueCost() + rightOutcome.falseCost()),
              leftOutcome.trueCost() + rightOutcome.trueCost()));
    }
    if (node.val == 3) {
      return new Outcome(
          Math.min(
              Math.min(
                  leftOutcome.falseCost() + rightOutcome.falseCost(),
                  leftOutcome.falseCost() + rightOutcome.trueCost()),
              leftOutcome.trueCost() + rightOutcome.falseCost()),
          leftOutcome.trueCost() + rightOutcome.trueCost());
    }
    if (node.val == 4) {
      return new Outcome(
          Math.min(
              leftOutcome.falseCost() + rightOutcome.falseCost(),
              leftOutcome.trueCost() + rightOutcome.trueCost()),
          Math.min(
              leftOutcome.falseCost() + rightOutcome.trueCost(),
              leftOutcome.trueCost() + rightOutcome.falseCost()));
    }

    return (leftOutcome == null)
        ? new Outcome(rightOutcome.trueCost(), rightOutcome.falseCost())
        : new Outcome(leftOutcome.trueCost(), leftOutcome.falseCost());
  }
}

record Outcome(int falseCost, int trueCost) {}
