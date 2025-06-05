// https://leetcode.com/problems/maximize-count-of-distinct-primes-after-split/solutions/6800954/c-solution-traditional-maximum-segment-tree-with-lazy-update-beat-90/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  static final int LIMIT = 100000;

  public int[] maximumCount(int[] nums, int[][] queries) {
    boolean[] primes = buildPrimes();

    Map<Integer, SortedSet<Integer>> primeToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      if (primes[nums[i]]) {
        primeToIndices.putIfAbsent(nums[i], new TreeSet<>());
        primeToIndices.get(nums[i]).add(i);
      }
    }

    Node segmentTree = buildSegmentTree(0, nums.length - 1);

    for (SortedSet<Integer> indices : primeToIndices.values()) {
      update(indices.first() + 1, indices.last(), 1, segmentTree);
    }

    int[] result = new int[queries.length];
    for (int i = 0; i < queries.length; ++i) {
      int index = queries[i][0];
      int oldValue = nums[index];
      int newValue = queries[i][1];
      nums[index] = newValue;

      if (primes[oldValue]) {
        SortedSet<Integer> indices = primeToIndices.get(oldValue);
        update(indices.first() + 1, indices.last(), -1, segmentTree);

        indices.remove(index);
        if (indices.isEmpty()) {
          primeToIndices.remove(oldValue);
        } else {
          indices = primeToIndices.get(oldValue);
          update(indices.first() + 1, indices.last(), 1, segmentTree);
        }
      }

      if (primes[newValue]) {
        if (primeToIndices.containsKey(newValue)) {
          SortedSet<Integer> indices = primeToIndices.get(newValue);
          update(indices.first() + 1, indices.last(), -1, segmentTree);
        }

        primeToIndices.putIfAbsent(newValue, new TreeSet<>());
        primeToIndices.get(newValue).add(index);

        SortedSet<Integer> indices = primeToIndices.get(newValue);
        update(indices.first() + 1, indices.last(), 1, segmentTree);
      }

      result[i] = primeToIndices.size() + query(0, nums.length - 1, segmentTree);
    }

    return result;
  }

  void update(int beginIndex, int endIndex, int delta, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        node.delta += delta;
      } else {
        node.left.delta += node.delta;
        node.right.delta += node.delta;
        node.delta = 0;

        update(beginIndex, endIndex, delta, node.left);
        update(beginIndex, endIndex, delta, node.right);

        node.max =
            Math.max(
                query(node.left.beginIndex, node.left.endIndex, node.left),
                query(node.right.beginIndex, node.right.endIndex, node.right));
      }
    }
  }

  int query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return 0;
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.max + node.delta;
    }

    node.left.delta += node.delta;
    node.right.delta += node.delta;
    node.delta = 0;

    node.max =
        Math.max(
            query(node.left.beginIndex, node.left.endIndex, node.left),
            query(node.right.beginIndex, node.right.endIndex, node.right));

    return Math.max(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
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

  void update(int[] extras, int beginIndex, int endIndex, int delta) {
    for (int i = beginIndex; i <= endIndex; ++i) {
      extras[i] += delta;
    }
  }

  boolean[] buildPrimes() {
    boolean[] primes = new boolean[LIMIT + 1];
    Arrays.fill(primes, true);
    primes[0] = false;
    primes[1] = false;
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i * 2; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }

    return primes;
  }
}

class Node {
  int beginIndex;
  int endIndex;
  int delta;
  int max;
  Node left;
  Node right;

  Node(int beginIndex, int endIndex) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }
}