import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
    List<Long> result = new ArrayList<>();
    LazySegTree lazySegTree = new LazySegTree(nums1);
    long sum = Arrays.stream(nums2).asLongStream().sum();
    for (int[] query : queries) {
      if (query[0] == 1) {
        lazySegTree.update(query[1], query[2]);
      } else if (query[0] == 2) {
        sum += (long) query[1] * lazySegTree.root.getComputedMinValue();
      } else {
        result.add(sum);
      }
    }

    return result.stream().mapToLong(Long::longValue).toArray();
  }
}

class LazySegTree {
  Node root;

  LazySegTree(int[] values) {
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, values[beginIndex], false, null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    Node left = buildNode(values, beginIndex, middleIndex);
    Node right = buildNode(values, middleIndex + 1, endIndex);

    return new Node(beginIndex, endIndex, left.sum + right.sum, false, left, right);
  }

  void update(int beginIndex, int endIndex) {
    update(beginIndex, endIndex, root);
  }

  private void update(int beginIndex, int endIndex, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        node.apply();
      } else {
        node.pushDown();

        update(beginIndex, endIndex, node.left);
        update(beginIndex, endIndex, node.right);

        node.pull();
      }
    }
  }

  int query(int beginIndex, int endIndex) {
    return query(beginIndex, endIndex, root);
  }

  private int query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return Integer.MAX_VALUE;
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.getComputedMinValue();
    }

    node.pushDown();

    node.pull();

    return Math.min(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int sum;
    boolean flipped;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, int sum, boolean flipped, Node left, Node right) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.sum = sum;
      this.flipped = flipped;
      this.left = left;
      this.right = right;
    }

    int getComputedMinValue() {
      return flipped ? (endIndex - beginIndex + 1 - sum) : sum;
    }

    void pushDown() {
      if (flipped) {
        left.apply();
        right.apply();

        flipped = false;
      }
    }

    void apply() {
      flipped ^= true;
    }

    void pull() {
      sum = left.getComputedMinValue() + right.getComputedMinValue();
    }
  }
}
