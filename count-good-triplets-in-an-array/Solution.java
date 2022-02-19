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

    int[] A = new int[Integer.highestOneBit(n) * 2 + 1];

    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, n).boxed().collect(Collectors.toMap(i -> nums2[i], i -> i));

    int[] result = new int[n];
    for (int i = 0; i < result.length; ++i) {
      result[i] = prefix_sum(A, valueToIndex.get(nums1[i]));
      add(A, valueToIndex.get(nums1[i]), 1);
    }

    return result;
  }

  int LSB(int i) {
    return i & -i;
  }

  int prefix_sum(int[] A, int i) {
    int sum = A[0];
    for (; i != 0; i -= LSB(i)) sum += A[i];
    return sum;
  }

  void add(int[] A, int i, int delta) {
    if (i == 0) {
      A[0] += delta;
      return;
    }
    for (; i < A.length; i += LSB(i)) A[i] += delta;
  }

  int[] buildRightSameNums(int[] nums1, int[] nums2) {
    return reverse(buildLeftSameNums(reverse(nums1), reverse(nums2)));
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }
}