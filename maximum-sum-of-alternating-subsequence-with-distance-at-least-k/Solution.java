import java.util.Arrays;
import java.util.stream.LongStream;

class Solution {
  public long maxAlternatingSum(int[] nums, int k) {
    int maxValue = Arrays.stream(nums).max().getAsInt();

    long[] bottomValues = new long[maxValue + 2];
    long[] topValues = new long[maxValue + 2];

    SegTree bottomSegTree = new SegTree(bottomValues);
    SegTree topSegTree = new SegTree(topValues);

    long[] bottomDp = new long[nums.length];
    long[] topDp = new long[nums.length];

    for (int i = 0; i < nums.length; ++i) {
      if (i >= k) {
        if (bottomDp[i - k] > bottomValues[nums[i - k]]) {
          bottomValues[nums[i - k]] = bottomDp[i - k];
          bottomSegTree.update(nums[i - k], bottomDp[i - k]);
        }
        if (topDp[i - k] > topValues[nums[i - k]]) {
          topValues[nums[i - k]] = topDp[i - k];
          topSegTree.update(nums[i - k], topDp[i - k]);
        }
      }

      bottomDp[i] = topSegTree.query(nums[i] + 1, maxValue) + nums[i];
      topDp[i] = bottomSegTree.query(0, nums[i] - 1) + nums[i];
    }

    return LongStream.concat(Arrays.stream(bottomDp), Arrays.stream(topDp)).max().getAsLong();
  }
}

class SegTree {
  Node root;

  SegTree(long[] values) {
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(long[] values, int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex);

    if (beginIndex == endIndex) {
      node.maxValue = values[beginIndex];
    } else {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(values, beginIndex, middleIndex);
      node.right = buildNode(values, middleIndex + 1, endIndex);

      node.pull();
    }

    return node;
  }

  void update(int index, long value) {
    update(index, value, root);
  }

  private void update(int index, long value, Node node) {
    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        node.maxValue = value;
      } else {
        update(index, value, node.left);
        update(index, value, node.right);

        node.pull();
      }
    }
  }

  long query(int beginIndex, int endIndex) {
    return query(beginIndex, endIndex, root);
  }

  private long query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return Long.MIN_VALUE;
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.maxValue;
    }

    return Math.max(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  static class Node {
    int beginIndex;
    int endIndex;
    long maxValue;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
    }

    void pull() {
      maxValue = Math.max(left.maxValue, right.maxValue);
    }
  }
}
