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

    int[] binaryIndexedTree = new int[Integer.highestOneBit(n) * 2 + 1];

    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, n).boxed().collect(Collectors.toMap(i -> nums2[i], i -> i));

    int[] result = new int[n];
    for (int i = 0; i < result.length; ++i) {
      result[i] = query(binaryIndexedTree, valueToIndex.get(nums1[i]) + 1);
      update(binaryIndexedTree, valueToIndex.get(nums1[i]) + 1, 1);
    }

    return result;
  }

  int query(int[] binaryIndexedTree, int index) {
    int result = 0;
    while (index != 0) {
      result += binaryIndexedTree[index];
      index -= index & -index;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int index, int delta) {
    while (index < binaryIndexedTree.length) {
      binaryIndexedTree[index] += delta;
      index += index & -index;
    }
  }

  int[] buildRightSameNums(int[] nums1, int[] nums2) {
    return reverse(buildLeftSameNums(reverse(nums1), reverse(nums2)));
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }
}