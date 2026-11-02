class Solution {
  public int countValidSubarrays(int[] nums, int x) {
    long[] prefixSums = new long[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    int[][] prefixCounts = new int[nums.length + 1][10];
    int lastDigit = 0;
    for (int i = 1; i < prefixCounts.length; ++i) {
      lastDigit = (lastDigit + nums[i - 1]) % 10;

      for (int d = 0; d < 10; ++d) {
        prefixCounts[i][d] = prefixCounts[i - 1][d] + ((d == lastDigit) ? 1 : 0);
      }
    }

    int result = 0;
    for (int leftIndex = 0; leftIndex < nums.length; ++leftIndex) {
      int beginIndex = leftIndex;
      while (beginIndex != nums.length) {
        long rangeSum = prefixSums[beginIndex + 1] - prefixSums[leftIndex];
        int firstDigit = computeFirstDigit(rangeSum);

        long limit = firstDigit + 1;
        while (limit <= rangeSum) {
          limit *= 10;
        }

        int endIndex = findEndIndex(prefixSums, leftIndex, beginIndex, limit);

        if (firstDigit == x) {
          int target = (int) ((firstDigit + prefixSums[leftIndex]) % 10);

          result += prefixCounts[endIndex + 1][target] - prefixCounts[beginIndex][target];
        }

        beginIndex = endIndex + 1;
      }
    }

    return result;
  }

  int computeFirstDigit(long x) {
    while (x >= 10) {
      x /= 10;
    }

    return (int) x;
  }

  int findEndIndex(long[] prefixSums, int leftIndex, int beginIndex, long limit) {
    int result = -1;
    int lower = beginIndex;
    int upper = prefixSums.length - 2;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (prefixSums[middle + 1] - prefixSums[leftIndex] < limit) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}