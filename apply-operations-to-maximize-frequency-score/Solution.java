import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxFrequencyScore(int[] nums, long k) {
    Arrays.sort(nums);

    long[] prefixSums = new long[nums.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + nums[i];
    }

    int result = -1;
    int lower = 1;
    int upper = nums.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(prefixSums, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(long[] prefixSums, long k, int length) {
    return IntStream.rangeClosed(0, prefixSums.length - length)
        .anyMatch(
            i ->
                computeRangeSum(prefixSums, i + length - (length + 1) / 2, i + length - 1)
                        - computeRangeSum(prefixSums, i, i + (length + 1) / 2 - 1)
                    <= k);
  }

  long computeRangeSum(long[] prefixSums, int beginIndex, int endIndex) {
    return prefixSums[endIndex] - ((beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]);
  }
}