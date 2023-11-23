class Solution {
  public long maxGcdSum(int[] nums, int k) {
    long[] prefixSums = new long[nums.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + nums[i];
    }

    int size = Integer.numberOfTrailingZeros(Integer.highestOneBit(nums.length)) + 1;
    int[][] g = new int[nums.length][size];
    for (int i = 0; i < g.length; ++i) {
      g[i][0] = nums[i];
    }
    for (int j = 1; j <= size; ++j) {
      for (int i = 0; i < g.length; ++i) {
        if (i + (1 << j) <= nums.length) {
          g[i][j] = gcd(g[i][j - 1], g[i + (1 << (j - 1))][j - 1]);
        }
      }
    }

    long result = -1;
    for (int beginIndex = 0; beginIndex + k <= nums.length; ++beginIndex) {
      int endIndex = beginIndex + k - 2;
      while (endIndex != nums.length - 1) {
        endIndex = findEndIndex(g, beginIndex, endIndex + 1);
        result =
            Math.max(
                result,
                computeRangeSum(prefixSums, beginIndex, endIndex)
                    * computeRangeGcd(g, beginIndex, endIndex));
      }
    }

    return result;
  }

  int findEndIndex(int[][] g, int beginIndex, int leftIndex) {
    int result = -1;
    int lower = leftIndex;
    int upper = g.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeRangeGcd(g, beginIndex, middle) == computeRangeGcd(g, beginIndex, leftIndex)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int computeRangeGcd(int[][] g, int beginIndex, int endIndex) {
    int e = Integer.numberOfTrailingZeros(Integer.highestOneBit(endIndex - beginIndex + 1));

    return gcd(g[beginIndex][e], g[endIndex - (1 << e) + 1][e]);
  }

  long computeRangeSum(long[] prefixSums, int beginIndex, int endIndex) {
    return prefixSums[endIndex] - ((beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}