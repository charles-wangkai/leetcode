// https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/solutions/4219104/segment-tree-with-range-update-o-n-log-n-time-o-n-space/

import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumCounts(int[] nums) {
    Node root = buildSegmentTree(0, nums.length - 1);

    int result = 0;
    Map<Integer, Integer> valueToLastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int lastIndex = valueToLastIndex.getOrDefault(nums[i], -1);
      update(lastIndex + 1, i, root);

      result = addMod(result, query(0, i, root).squareSum());

      valueToLastIndex.put(nums[i], i);
    }

    return result;
  }

  void update(int beginIndex, int endIndex, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        ++node.delta;
      } else {
        node.left.delta += node.delta;
        node.right.delta += node.delta;
        node.delta = 0;

        update(beginIndex, endIndex, node.left);
        update(beginIndex, endIndex, node.right);

        Outcome leftOutcome = query(node.left.beginIndex, node.left.endIndex, node.left);
        Outcome rightOutcome = query(node.right.beginIndex, node.right.endIndex, node.right);
        node.squareSum = addMod(leftOutcome.squareSum(), rightOutcome.squareSum());
        node.sum = addMod(leftOutcome.sum(), rightOutcome.sum());
      }
    }
  }

  Outcome query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return new Outcome(0, 0);
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return new Outcome(
          addMod(
              node.squareSum,
              addMod(
                  multiplyMod(multiplyMod(2, node.sum), node.delta),
                  multiplyMod(
                      node.endIndex - node.beginIndex + 1, multiplyMod(node.delta, node.delta)))),
          addMod(node.sum, multiplyMod(node.endIndex - node.beginIndex + 1, node.delta)));
    }

    node.left.delta += node.delta;
    node.right.delta += node.delta;
    node.delta = 0;

    Outcome leftOutcome = query(node.left.beginIndex, node.left.endIndex, node.left);
    Outcome rightOutcome = query(node.right.beginIndex, node.right.endIndex, node.right);
    node.squareSum = addMod(leftOutcome.squareSum(), rightOutcome.squareSum());
    node.sum = addMod(leftOutcome.sum(), rightOutcome.sum());

    leftOutcome = query(beginIndex, endIndex, node.left);
    rightOutcome = query(beginIndex, endIndex, node.right);

    return new Outcome(
        addMod(leftOutcome.squareSum(), rightOutcome.squareSum()),
        addMod(leftOutcome.sum(), rightOutcome.sum()));
  }

  Node buildSegmentTree(int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex);
    if (beginIndex != endIndex) {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildSegmentTree(beginIndex, middleIndex);
      node.right = buildSegmentTree(middleIndex + 1, endIndex);
    }

    return node;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}

class Node {
  int beginIndex;
  int endIndex;
  int delta;
  int squareSum;
  int sum;
  Node left;
  Node right;

  Node(int beginIndex, int endIndex) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }
}

record Outcome(int squareSum, int sum) {}
