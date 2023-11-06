import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long maxBalancedSubsequenceSum(int[] nums) {
    if (Arrays.stream(nums).allMatch(x -> x <= 0)) {
      return Arrays.stream(nums).max().getAsInt();
    }

    int[] sortedValues =
        IntStream.range(0, nums.length)
            .filter(i -> nums[i] > 0)
            .map(i -> nums[i] - i)
            .sorted()
            .distinct()
            .toArray();
    Map<Integer, Integer> valueToPos =
        IntStream.range(0, sortedValues.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedValues[i], i -> i));

    Node root =
        buildSegmentTree(
            IntStream.range(0, nums.length)
                .filter(i -> nums[i] > 0)
                .map(i -> valueToPos.get(nums[i] - i))
                .min()
                .getAsInt(),
            IntStream.range(0, nums.length)
                .filter(i -> nums[i] > 0)
                .map(i -> valueToPos.get(nums[i] - i))
                .max()
                .getAsInt());
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] > 0) {
        update(
            valueToPos.get(nums[i] - i),
            query(Integer.MIN_VALUE, valueToPos.get(nums[i] - i), root) + nums[i],
            root);
      }
    }

    return query(Integer.MIN_VALUE, Integer.MAX_VALUE, root);
  }

  void update(int pos, long sum, Node node) {
    if (node.begin <= pos && node.end >= pos) {
      if (node.begin == node.end) {
        node.maxSum = Math.max(node.maxSum, sum);
      } else {
        update(pos, sum, node.left);
        update(pos, sum, node.right);
        node.maxSum = Math.max(node.left.maxSum, node.right.maxSum);
      }
    }
  }

  long query(int begin, int end, Node node) {
    if (node.begin > end || node.end < begin) {
      return 0;
    }
    if (node.begin >= begin && node.end <= end) {
      return node.maxSum;
    }

    return Math.max(query(begin, end, node.left), query(begin, end, node.right));
  }

  Node buildSegmentTree(int begin, int end) {
    Node node = new Node(begin, end);
    if (begin != end) {
      int middle = Math.floorDiv(begin + end, 2);
      node.left = buildSegmentTree(begin, middle);
      node.right = buildSegmentTree(middle + 1, end);
    }

    return node;
  }
}

class Node {
  int begin;
  int end;
  long maxSum;
  Node left;
  Node right;

  Node(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }
}
