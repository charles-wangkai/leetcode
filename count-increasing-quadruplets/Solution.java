// https://en.wikipedia.org/wiki/Fenwick_tree

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long countQuadruplets(int[] nums) {
    long result = 0;
    for (int k = 1; k < nums.length; ++k) {
      int[] rightSorted = IntStream.range(k + 1, nums.length).map(i -> nums[i]).sorted().toArray();

      int[] A = new int[Integer.highestOneBit(nums.length) * 2 + 1];
      for (int j = 0; j < k; ++j) {
        if (nums[j] > nums[k]) {
          result += prefix_sum(A, nums[k]) * computeRightNum(rightSorted, nums[j]);
        }

        add(A, nums[j], 1);
      }
    }

    return result;
  }

  int computeRightNum(int[] rightSorted, int target) {
    return rightSorted.length - (-1 - Arrays.binarySearch(rightSorted, target));
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
}
