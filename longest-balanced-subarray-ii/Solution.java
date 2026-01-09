// https://leetcode.com/problems/longest-balanced-subarray-ii/solutions/7285186/easiest-solution-with-explanation/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestBalanced(int[] nums) {
    int result = 0;
    Map<Integer, Integer> valueToPrevIndex = new HashMap<>();
    LazySegTree lazySegTree = new LazySegTree(nums);
    for (int r = 0; r < nums.length; ++r) {
      int delta = (nums[r] % 2 == 0) ? 1 : -1;

      if (valueToPrevIndex.containsKey(nums[r])) {
        lazySegTree.update(0, valueToPrevIndex.get(nums[r]), -delta);
      }
      lazySegTree.update(0, r, delta);

      valueToPrevIndex.put(nums[r], r);

      int l = lazySegTree.findLeftmostZero();
      if (l <= r) {
        result = Math.max(result, r - l + 1);
      }
    }

    return result;
  }
}

class LazySegTree {
  Node root;

  LazySegTree(int[] values) {
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, 0, 0, 0, null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    Node left = buildNode(values, beginIndex, middleIndex);
    Node right = buildNode(values, middleIndex + 1, endIndex);

    return new Node(
        beginIndex,
        endIndex,
        0,
        Math.min(left.min, right.min),
        Math.max(left.max, right.max),
        left,
        right);
  }

  void update(int beginIndex, int endIndex, int delta) {
    update(beginIndex, endIndex, delta, root);
  }

  private void update(int beginIndex, int endIndex, int delta, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        node.apply(delta);
      } else {
        node.pushDown();

        update(beginIndex, endIndex, delta, node.left);
        update(beginIndex, endIndex, delta, node.right);

        node.pull();
      }
    }
  }

  int findLeftmostZero() {
    return findLeftmostZero(root);
  }

  private int findLeftmostZero(Node node) {
    if (node.getComputedMinValue() > 0 || node.getComputedMaxValue() < 0) {
      return Integer.MAX_VALUE;
    }

    if (node.beginIndex == node.endIndex) {
      return (node.getComputedMinValue() == 0) ? node.beginIndex : Integer.MAX_VALUE;
    }

    node.pushDown();

    node.pull();

    int result = findLeftmostZero(node.left);

    return (result == Integer.MAX_VALUE) ? findLeftmostZero(node.right) : result;
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int delta;
    int min;
    int max;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, int delta, int min, int max, Node left, Node right) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.delta = delta;
      this.min = min;
      this.max = max;
      this.left = left;
      this.right = right;
    }

    int getComputedMinValue() {
      return min + delta;
    }

    int getComputedMaxValue() {
      return max + delta;
    }

    void pushDown() {
      if (delta != 0) {
        left.apply(delta);
        right.apply(delta);

        delta = 0;
      }
    }

    void apply(int d) {
      delta += d;
    }

    void pull() {
      min = Math.min(left.getComputedMinValue(), right.getComputedMinValue());
      max = Math.max(left.getComputedMaxValue(), right.getComputedMaxValue());
    }
  }
}
