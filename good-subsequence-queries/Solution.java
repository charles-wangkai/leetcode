// https://leetcode.com/problems/good-subsequence-queries/solutions/7877050/easy-to-understand-segment-tree-solution-kw9s/

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countGoodSubseq(int[] nums, int p, int[][] queries) {
    SegTree segTree = new SegTree(nums, p);
    int multipleCount = (int) Arrays.stream(nums).filter(x -> x % p == 0).count();

    int result = 0;
    for (int[] query : queries) {
      int index = query[0];
      int value = query[1];

      if (nums[index] % p == 0) {
        --multipleCount;
      }
      if (value % p == 0) {
        ++multipleCount;
      }
      nums[index] = value;

      segTree.update(index, value);

      if (segTree.query(0, nums.length - 1) == 1
          && (multipleCount != nums.length
              || IntStream.range(0, nums.length)
                  .anyMatch(
                      i ->
                          gcd(
                                  (i == 0) ? 0 : segTree.query(0, i - 1),
                                  (i == nums.length - 1)
                                      ? 0
                                      : segTree.query(i + 1, nums.length - 1))
                              == 1))) {
        ++result;
      }
    }

    return result;
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

class SegTree {
  int p;
  Node root;

  SegTree(int[] values, int p) {
    this.p = p;
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex);

    if (beginIndex == endIndex) {
      node.g = (values[beginIndex] % p == 0) ? (values[beginIndex] / p) : 0;
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
        node.g = (value % p == 0) ? (value / p) : 0;
      } else {
        update(index, value, node.left);
        update(index, value, node.right);

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
      return node.g;
    }

    return Solution.gcd(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int g;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
    }

    void pull() {
      g = Solution.gcd(left.g, right.g);
    }
  }
}
