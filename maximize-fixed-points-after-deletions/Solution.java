import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int maxFixedPoints(int[] nums) {
    int[] sortedValues =
        IntStream.range(0, nums.length)
            .filter(i -> i >= nums[i])
            .boxed()
            .sorted(Comparator.comparing(i -> i - nums[i]))
            .mapToInt(i -> nums[i])
            .toArray();

    int maxValue = Arrays.stream(nums).max().getAsInt();
    SegTree segTree = new SegTree(new int[maxValue + 1]);
    for (int value : sortedValues) {
      segTree.update(value, ((value == 0) ? 0 : segTree.query(0, value - 1)) + 1);
    }

    return segTree.query(0, maxValue);
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
      node.maxValue = values[beginIndex];
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
        node.maxValue = value;
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
      return Integer.MIN_VALUE;
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
    int maxValue;
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
