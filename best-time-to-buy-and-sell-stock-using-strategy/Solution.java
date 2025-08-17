import java.util.stream.IntStream;

class Solution {
  public long maxProfit(int[] prices, int[] strategy, int k) {
    int n = prices.length;

    long[] originalPrefixSums = new long[n];
    for (int i = 0; i < originalPrefixSums.length; ++i) {
      originalPrefixSums[i] = ((i == 0) ? 0 : originalPrefixSums[i - 1]) + prices[i] * strategy[i];
    }

    long[] originalSuffixSums = new long[n];
    for (int i = originalSuffixSums.length - 1; i >= 0; --i) {
      originalSuffixSums[i] =
          ((i == originalSuffixSums.length - 1) ? 0 : originalSuffixSums[i + 1])
              + prices[i] * strategy[i];
    }

    long[] modifiedPrefixSums = new long[n];
    for (int i = 0; i < modifiedPrefixSums.length; ++i) {
      modifiedPrefixSums[i] = ((i == 0) ? 0 : modifiedPrefixSums[i - 1]) + prices[i];
    }

    return Math.max(
        originalPrefixSums[n - 1],
        IntStream.rangeClosed(0, n - k)
            .mapToLong(
                beginIndex ->
                    ((beginIndex == 0) ? 0 : originalPrefixSums[beginIndex - 1])
                        + ((beginIndex + k == n) ? 0 : originalSuffixSums[beginIndex + k])
                        + (modifiedPrefixSums[beginIndex + k - 1]
                            - modifiedPrefixSums[beginIndex + k / 2 - 1]))
            .max()
            .getAsLong());
  }
}