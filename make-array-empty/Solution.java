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
    int[] A = new int[Integer.highestOneBit(nums.length) * 2 + 1];
    add(A, sortedIndices[0], 1);
    for (int i = 1; i < sortedIndices.length; ++i) {
      add(A, sortedIndices[i], 1);

      if (sortedIndices[i] < sortedIndices[i - 1]) {
        result += rangeSum(A, sortedIndices[i], sortedIndices[i - 1]) - 1;
      } else {
        result += i - rangeSum(A, sortedIndices[i - 1], sortedIndices[i]);
      }
    }

    return result;
  }

  int LSB(int i) {
    return i & -i;
  }

  void add(int[] A, int i, int delta) {
    if (i == 0) {
      A[0] += delta;
      return;
    }
    for (; i < A.length; i += LSB(i)) A[i] += delta;
  }

  int rangeSum(int[] A, int i, int j) {
    int sum = 0;
    for (; j > i; j -= LSB(j)) sum += A[j];
    for (; i > j; i -= LSB(i)) sum -= A[i];
    return sum;
  }
}
