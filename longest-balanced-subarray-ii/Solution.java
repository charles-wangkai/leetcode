// https://leetcode.com/problems/longest-balanced-subarray-ii/solutions/7285186/easiest-solution-with-explanation/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestBalanced(int[] nums) {
    int result = 0;
    Map<Integer, Integer> valueToPrevIndex = new HashMap<>();
    Node segmentTree = buildNode(0, nums.length - 1);
    for (int r = 0; r < nums.length; ++r) {
      int delta = (nums[r] % 2 == 0) ? 1 : -1;

      if (valueToPrevIndex.containsKey(nums[r])) {
        update(0, valueToPrevIndex.get(nums[r]), -delta, segmentTree);
      }
      update(0, r, delta, segmentTree);

      valueToPrevIndex.put(nums[r], r);

      int l = findLeftmostZero(segmentTree);
      if (l <= r) {
        result = Math.max(result, r - l + 1);
      }
    }

    return result;
  }

  void pushDown(Node node) {
    if (node.delta != 0) {
      node.min += node.delta;
      node.max += node.delta;

      if (node.beginIndex != node.endIndex) {
        node.left.delta += node.delta;
        node.right.delta += node.delta;
      }

      node.delta = 0;
    }
  }

  int findLeftmostZero(Node node) {
    pushDown(node);

    if (queryMin(node) > 0 || queryMax(node) < 0) {
      return Integer.MAX_VALUE;
    }

    if (node.beginIndex == node.endIndex) {
      return (queryMin(node) == 0) ? node.beginIndex : Integer.MAX_VALUE;
    }

    int result = findLeftmostZero(node.left);

    return (result == Integer.MAX_VALUE) ? findLeftmostZero(node.right) : result;
  }

  void update(int beginIndex, int endIndex, int delta, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        node.delta += delta;
      } else {
        pushDown(node);

        update(beginIndex, endIndex, delta, node.left);
        update(beginIndex, endIndex, delta, node.right);

        node.min = Math.min(queryMin(node.left), queryMin(node.right));
        node.max = Math.max(queryMax(node.left), queryMax(node.right));
      }
    }
  }

  int queryMax(Node node) {
    return node.max + node.delta;
  }

  int queryMin(Node node) {
    return node.min + node.delta;
  }

  Node buildNode(int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, 0, 0, 0, null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    Node left = buildNode(beginIndex, middleIndex);
    Node right = buildNode(middleIndex + 1, endIndex);

    return new Node(
        beginIndex,
        endIndex,
        0,
        Math.min(left.min, right.min),
        Math.max(left.max, right.max),
        left,
        right);
  }
}

class Node {
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
}
