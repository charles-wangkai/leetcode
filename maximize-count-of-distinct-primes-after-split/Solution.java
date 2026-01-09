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

    LazySegTree lazySegTree = new LazySegTree(nums.length);

    for (SortedSet<Integer> indices : primeToIndices.values()) {
      lazySegTree.update(indices.first() + 1, indices.last(), 1);
    }

    int[] result = new int[queries.length];
    for (int i = 0; i < queries.length; ++i) {
      int index = queries[i][0];
      int oldValue = nums[index];
      int newValue = queries[i][1];
      nums[index] = newValue;

      if (primes[oldValue]) {
        SortedSet<Integer> indices = primeToIndices.get(oldValue);
        lazySegTree.update(indices.first() + 1, indices.last(), -1);

        indices.remove(index);
        if (indices.isEmpty()) {
          primeToIndices.remove(oldValue);
        } else {
          indices = primeToIndices.get(oldValue);
          lazySegTree.update(indices.first() + 1, indices.last(), 1);
        }
      }

      if (primes[newValue]) {
        if (primeToIndices.containsKey(newValue)) {
          SortedSet<Integer> indices = primeToIndices.get(newValue);
          lazySegTree.update(indices.first() + 1, indices.last(), -1);
        }

        primeToIndices.putIfAbsent(newValue, new TreeSet<>());
        primeToIndices.get(newValue).add(index);

        SortedSet<Integer> indices = primeToIndices.get(newValue);
        lazySegTree.update(indices.first() + 1, indices.last(), 1);
      }

      result[i] = primeToIndices.size() + lazySegTree.query(0, nums.length - 1);
    }

    return result;
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

class LazySegTree {
  Node root;

  LazySegTree(int n) {
    root = buildNode(0, n - 1);
  }

  private Node buildNode(int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex);
    if (beginIndex != endIndex) {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(beginIndex, middleIndex);
      node.right = buildNode(middleIndex + 1, endIndex);
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

  int query(int beginIndex, int endIndex) {
    return query(beginIndex, endIndex, root);
  }

  private int query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return 0;
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.getComputedMax();
    }

    node.pushDown();

    node.pull();

    return Math.max(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  static class Node {
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

    int getComputedMax() {
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
      max = Math.max(left.getComputedMax(), right.getComputedMax());
    }
  }
}
