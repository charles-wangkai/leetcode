import java.util.stream.IntStream;

class Solution {
  public long countSubarrays(int[] nums, long k) {
    long[] prefixSums = new long[nums.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + nums[i];
    }

    return IntStream.range(0, nums.length)
        .map(i -> computeSubarrayNum(k, prefixSums, i))
        .asLongStream()
        .sum();
  }

  int computeSubarrayNum(long k, long[] prefixSums, int beginIndex) {
    int index = beginIndex - 1;
    int lowerIndex = beginIndex;
    int upperIndex = prefixSums.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if (computeRangeSum(prefixSums, beginIndex, middleIndex) * (middleIndex - beginIndex + 1)
          < k) {
        index = middleIndex;
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return index - beginIndex + 1;
  }

  long computeRangeSum(long[] prefixSums, int beginIndex, int endIndex) {
    return prefixSums[endIndex] - ((beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]);
  }
}