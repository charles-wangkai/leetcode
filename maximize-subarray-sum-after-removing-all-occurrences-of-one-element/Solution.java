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

    Node segmentTree = buildNode(nums, 0, nums.length - 1);

    long result = segmentTree.maxSum;
    for (int value : valueToIndices.keySet()) {
      List<Integer> indices = valueToIndices.get(value);
      if (indices.size() != nums.length) {
        for (int index : indices) {
          update(index, 0, segmentTree);
        }

        result = Math.max(result, segmentTree.maxSum);

        for (int index : indices) {
          update(index, value, segmentTree);
        }
      }
    }

    return result;
  }

  void update(int index, int value, Node node) {
    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        node.totalSum = value;
        node.maxPrefixSum = value;
        node.maxSuffixSum = value;
        node.maxSum = value;
      } else {
        update(index, value, node.left);
        update(index, value, node.right);
        merge(node);
      }
    }
  }

  Node buildNode(int[] nums, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(
          beginIndex,
          endIndex,
          nums[beginIndex],
          nums[beginIndex],
          nums[beginIndex],
          nums[beginIndex],
          null,
          null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    Node node =
        new Node(
            beginIndex,
            endIndex,
            -1,
            -1,
            -1,
            -1,
            buildNode(nums, beginIndex, middleIndex),
            buildNode(nums, middleIndex + 1, endIndex));
    merge(node);

    return node;
  }

  void merge(Node node) {
    node.totalSum = node.left.totalSum + node.right.totalSum;
    node.maxPrefixSum =
        Math.max(node.left.maxPrefixSum, node.left.totalSum + node.right.maxPrefixSum);
    node.maxSuffixSum =
        Math.max(node.right.maxSuffixSum, node.right.totalSum + node.left.maxSuffixSum);
    node.maxSum =
        Math.max(
            Math.max(node.left.maxSum, node.right.maxSum),
            node.left.maxSuffixSum + node.right.maxPrefixSum);
  }
}

class Node {
  int beginIndex;
  int endIndex;
  long totalSum;
  long maxPrefixSum;
  long maxSuffixSum;
  long maxSum;
  Node left;
  Node right;

  Node(
      int beginIndex,
      int endIndex,
      long totalSum,
      long maxPrefixSum,
      long maxSuffixSum,
      long maxSum,
      Node left,
      Node right) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
    this.totalSum = totalSum;
    this.maxPrefixSum = maxPrefixSum;
    this.maxSuffixSum = maxSuffixSum;
    this.maxSum = maxSum;
    this.left = left;
    this.right = right;
  }
}
