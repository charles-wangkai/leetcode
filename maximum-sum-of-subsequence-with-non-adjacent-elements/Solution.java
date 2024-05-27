class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maximumSumSubsequence(int[] nums, int[][] queries) {
    Node segmentTree = buildNode(nums, 0, nums.length - 1);

    long result = 0;
    for (int[] query : queries) {
      update(query[0], query[1], segmentTree);
      result += segmentTree.maxSum11;
    }

    return (int) (result % MODULUS);
  }

  void update(int index, int value, Node node) {
    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        node.maxSum11 = Math.max(0, value);
      } else {
        update(index, value, node.left);
        update(index, value, node.right);
        updateMaxSums(node);
      }
    }
  }

  Node buildNode(int[] nums, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, 0, 0, 0, Math.max(0, nums[beginIndex]), null, null);
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
    updateMaxSums(node);

    return node;
  }

  void updateMaxSums(Node node) {
    node.maxSum00 =
        Math.max(
            node.left.maxSum00 + node.right.maxSum10, node.left.maxSum01 + node.right.maxSum00);
    node.maxSum01 =
        Math.max(
            node.left.maxSum00 + node.right.maxSum11, node.left.maxSum01 + node.right.maxSum01);
    node.maxSum10 =
        Math.max(
            node.left.maxSum10 + node.right.maxSum10, node.left.maxSum11 + node.right.maxSum00);
    node.maxSum11 =
        Math.max(
            node.left.maxSum10 + node.right.maxSum11, node.left.maxSum11 + node.right.maxSum01);
  }
}

class Node {
  int beginIndex;
  int endIndex;
  long maxSum00;
  long maxSum01;
  long maxSum10;
  long maxSum11;
  Node left;
  Node right;

  Node(
      int beginIndex,
      int endIndex,
      long maxSum00,
      long maxSum01,
      long maxSum10,
      long maxSum11,
      Node left,
      Node right) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
    this.maxSum00 = maxSum00;
    this.maxSum01 = maxSum01;
    this.maxSum10 = maxSum10;
    this.maxSum11 = maxSum11;
    this.left = left;
    this.right = right;
  }
}