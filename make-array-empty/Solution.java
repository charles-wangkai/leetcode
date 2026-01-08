// https://en.wikipedia.org/wiki/Fenwick_tree

import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long countOperationsToEmptyArray(int[] nums) {
    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> nums[i]).reversed())
            .mapToInt(Integer::intValue)
            .toArray();

    long result = nums.length + sortedIndices[sortedIndices.length - 1];
    FenwickTree fenwickTree = new FenwickTree(nums.length);
    fenwickTree.add(sortedIndices[0] + 1, 1);
    for (int i = 1; i < sortedIndices.length; ++i) {
      fenwickTree.add(sortedIndices[i] + 1, 1);

      if (sortedIndices[i] < sortedIndices[i - 1]) {
        result +=
            fenwickTree.computePrefixSum(sortedIndices[i - 1] + 1)
                - fenwickTree.computePrefixSum(sortedIndices[i] + 1)
                - 1;
      } else {
        result +=
            i
                - (fenwickTree.computePrefixSum(sortedIndices[i] + 1)
                    - fenwickTree.computePrefixSum(sortedIndices[i - 1] + 1));
      }
    }

    return result;
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
