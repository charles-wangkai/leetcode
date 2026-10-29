// https://leetcode.com/problems/minimum-operations-to-stabilize-a-binary-tree/solutions/8571853/slope-trick-tree-dp-on-log2-n-by-crfmn-qwj5/

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
  public long stabilizeBinaryTree(TreeNode root) {
    return search(root).minCost;
  }

  Outcome search(TreeNode node) {
    Outcome outcome =
        new Outcome(new PriorityQueue<>(Comparator.reverseOrder()), new PriorityQueue<>(), 0, 0, 0);

    if (node.left != null) {
      outcome = merge(outcome, expand(search(node.left)));
    }
    if (node.right != null) {
      outcome = merge(outcome, expand(search(node.right)));
    }

    outcome.addLeftHinge(node.val);
    outcome.addRightHinge(node.val);

    return outcome;
  }

  Outcome merge(Outcome o1, Outcome o2) {
    if (o1.left.size() < o2.left.size()) {
      return merge(o2, o1);
    }

    o1.minCost += o2.minCost;

    for (int l : o2.left) {
      o1.addLeftHinge(l + o2.leftOffset);
    }
    for (int r : o2.right) {
      o1.addRightHinge(r + o2.rightOffset);
    }

    return o1;
  }

  Outcome expand(Outcome outcome) {
    --outcome.leftOffset;
    ++outcome.rightOffset;

    return outcome;
  }
}

class Outcome {
  PriorityQueue<Integer> left;
  PriorityQueue<Integer> right;
  int leftOffset;
  int rightOffset;
  long minCost;

  Outcome(
      PriorityQueue<Integer> left,
      PriorityQueue<Integer> right,
      int leftOffset,
      int rightOffset,
      long minCost) {
    this.left = left;
    this.right = right;
    this.leftOffset = leftOffset;
    this.rightOffset = rightOffset;
    this.minCost = minCost;
  }

  void addLeftHinge(int value) {
    if (!right.isEmpty() && value > right.peek() + rightOffset) {
      minCost += value - (right.peek() + rightOffset);

      left.offer((right.poll() + rightOffset) - leftOffset);
      right.offer(value - rightOffset);
    } else {
      left.offer(value - leftOffset);
    }
  }

  void addRightHinge(int value) {
    if (!left.isEmpty() && value < left.peek() + leftOffset) {
      minCost += (left.peek() + leftOffset) - value;

      right.offer((left.poll() + leftOffset) - rightOffset);
      left.offer(value - leftOffset);
    } else {
      right.offer(value - rightOffset);
    }
  }
}
