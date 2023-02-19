import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
    Node segmentTree = buildSegmentTree(nums1, 0, nums1.length - 1);

    List<Long> result = new ArrayList<>();
    long sum = Arrays.stream(nums2).asLongStream().sum();
    for (int[] query : queries) {
      if (query[0] == 1) {
        update(query[1], query[2], segmentTree);
      } else if (query[0] == 2) {
        sum += (long) query[1] * query(segmentTree);
      } else {
        result.add(sum);
      }
    }

    return result.stream().mapToLong(Long::longValue).toArray();
  }

  int query(Node node) {
    return node.flipped ? (node.endIndex - node.beginIndex + 1 - node.sum) : node.sum;
  }

  void update(int beginIndex, int endIndex, Node node) {
    if (!(beginIndex > node.endIndex || endIndex < node.beginIndex)) {
      if (beginIndex <= node.beginIndex && endIndex >= node.endIndex) {
        node.flipped ^= true;
      } else {
        update(beginIndex, endIndex, node.left);
        update(beginIndex, endIndex, node.right);

        node.sum = query(node.left) + query(node.right);
      }
    }
  }

  Node buildSegmentTree(int[] nums1, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, nums1[beginIndex], false, null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    Node left = buildSegmentTree(nums1, beginIndex, middleIndex);
    Node right = buildSegmentTree(nums1, middleIndex + 1, endIndex);

    return new Node(beginIndex, endIndex, left.sum + right.sum, false, left, right);
  }
}

class Node {
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
}
