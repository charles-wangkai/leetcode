import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int kthSmallestSubarraySum(int[] nums, int k) {
    int[] prefixSums = new int[nums.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + nums[i];
    }

    int result = -1;
    int lower = Arrays.stream(nums).min().getAsInt();
    int upper = Arrays.stream(nums).sum();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(prefixSums, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] prefixSums, int k, int sum) {
    return IntStream.range(0, prefixSums.length)
            .map(beginIndex -> computeLessEqualNum(prefixSums, beginIndex, sum))
            .sum()
        >= k;
  }

  int computeLessEqualNum(int[] prefixSums, int beginIndex, int sum) {
    int result = 0;
    int lower = beginIndex;
    int upper = prefixSums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (prefixSums[middle] - ((beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]) <= sum) {
        result = middle - beginIndex + 1;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}