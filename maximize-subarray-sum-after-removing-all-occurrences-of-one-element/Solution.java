// https://leetcode.com/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element/solutions/6230430/java-solution-using-segment-tree/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long maxSubarraySum(int[] nums) {
    int maxValue = Arrays.stream(nums).max().getAsInt();
    if (maxValue < 0) {
      return maxValue;
    }

    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    SegTree segTree = new SegTree(nums);

    long result = segTree.root.maxSum;
    for (int value : valueToIndices.keySet()) {
      List<Integer> indices = valueToIndices.get(value);
      if (indices.size() != nums.length) {
        for (int index : indices) {
          segTree.update(index, 0);
        }

        result = Math.max(result, segTree.root.maxSum);

        for (int index : indices) {
          segTree.update(index, value);
        }
      }
    }

    return result;
  }
}

class SegTree {
  Node root;

  SegTree(int[] values) {
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex);

    if (beginIndex == endIndex) {
      node.totalSum = values[beginIndex];
      node.maxPrefixSum = values[beginIndex];
      node.maxSuffixSum = values[beginIndex];
      node.maxSum = values[beginIndex];
    } else {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(values, beginIndex, middleIndex);
      node.right = buildNode(values, middleIndex + 1, endIndex);

      node.pull();
    }

    return node;
  }

  void update(int index, int value) {
    update(index, value, root);
  }

  private void update(int index, int value, Node node) {
    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        node.totalSum = value;
        node.maxPrefixSum = value;
        node.maxSuffixSum = value;
        node.maxSum = value;
      } else {
        update(index, value, node.left);
        update(index, value, node.right);

        node.pull();
      }
    }
  }

  static class Node {
    int beginIndex;
    int endIndex;
    long totalSum;
    long maxPrefixSum;
    long maxSuffixSum;
    long maxSum;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
    }

    void pull() {
      totalSum = left.totalSum + right.totalSum;
      maxPrefixSum = Math.max(left.maxPrefixSum, left.totalSum + right.maxPrefixSum);
      maxSuffixSum = Math.max(right.maxSuffixSum, right.totalSum + left.maxSuffixSum);
      maxSum =
          Math.max(Math.max(left.maxSum, right.maxSum), left.maxSuffixSum + right.maxPrefixSum);
    }
  }
}
