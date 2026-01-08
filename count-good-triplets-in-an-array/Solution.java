// https://en.wikipedia.org/wiki/Fenwick_tree

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long goodTriplets(int[] nums1, int[] nums2) {
    int n = nums1.length;

    int[] leftSameNums = buildLeftSameNums(nums1, nums2);
    int[] rightSameNums = buildRightSameNums(nums1, nums2);

    return IntStream.range(0, n).mapToLong(i -> (long) leftSameNums[i] * rightSameNums[i]).sum();
  }

  int[] buildLeftSameNums(int[] nums1, int[] nums2) {
    int n = nums1.length;

    FenwickTree fenwickTree = new FenwickTree(n);
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, n).boxed().collect(Collectors.toMap(i -> nums2[i], i -> i));

    int[] result = new int[n];
    for (int i = 0; i < result.length; ++i) {
      result[i] = fenwickTree.computePrefixSum(valueToIndex.get(nums1[i]) + 1);
      fenwickTree.add(valueToIndex.get(nums1[i]) + 1, 1);
    }

    return result;
  }

  int[] buildRightSameNums(int[] nums1, int[] nums2) {
    return reverse(buildLeftSameNums(reverse(nums1), reverse(nums2)));
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
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
