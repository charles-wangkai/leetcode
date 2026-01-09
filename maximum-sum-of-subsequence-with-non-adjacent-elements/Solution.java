class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maximumSumSubsequence(int[] nums, int[][] queries) {
    long result = 0;
    SegTree segTree = new SegTree(nums);
    for (int[] query : queries) {
      segTree.update(query[0], query[1]);
      result += segTree.root.maxSum11;
    }

    return (int) (result % MODULUS);
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
      node.maxSum00 = 0;
      node.maxSum01 = 0;
      node.maxSum10 = 0;
      node.maxSum11 = Math.max(0, values[beginIndex]);
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
        node.maxSum11 = Math.max(0, value);
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
    long maxSum00;
    long maxSum01;
    long maxSum10;
    long maxSum11;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
    }

    void pull() {
      maxSum00 = Math.max(left.maxSum00 + right.maxSum10, left.maxSum01 + right.maxSum00);
      maxSum01 = Math.max(left.maxSum00 + right.maxSum11, left.maxSum01 + right.maxSum01);
      maxSum10 = Math.max(left.maxSum10 + right.maxSum10, left.maxSum11 + right.maxSum00);
      maxSum11 = Math.max(left.maxSum10 + right.maxSum11, left.maxSum11 + right.maxSum01);
    }
  }
}
