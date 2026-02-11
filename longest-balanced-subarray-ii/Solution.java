// https://leetcode.com/problems/longest-balanced-subarray-ii/solutions/7285186/easiest-solution-with-explanation/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestBalanced(int[] nums) {
    int result = 0;
    Map<Integer, Integer> valueToPrevIndex = new HashMap<>();
    LazySegTree lazySegTree = new LazySegTree(nums.length);
    for (int r = 0; r < nums.length; ++r) {
      lazySegTree.update(
          valueToPrevIndex.getOrDefault(nums[r], -1) + 1, r, (nums[r] % 2 == 0) ? 1 : -1);

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

  LazySegTree(int size) {
    root = buildNode(0, size - 1);
  }

  private Node buildNode(int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex, 0);

    if (beginIndex == endIndex) {
      node.min = 0;
      node.max = 0;
    } else {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(beginIndex, middleIndex);
      node.right = buildNode(middleIndex + 1, endIndex);

      node.pull();
    }

    return node;
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

    int leftIndex = findLeftmostZero(node.left);

    return (leftIndex == Integer.MAX_VALUE) ? findLeftmostZero(node.right) : leftIndex;
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int delta;
    int min;
    int max;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, int delta) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.delta = delta;
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
