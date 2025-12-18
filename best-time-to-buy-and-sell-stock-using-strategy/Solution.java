import java.util.stream.IntStream;

class Solution {
  public long maxProfit(int[] prices, int[] strategy, int k) {
    int n = prices.length;

    long[] originalPrefixSums =
        buildPrefixSums(IntStream.range(0, n).map(i -> prices[i] * strategy[i]).toArray());
    long[] modifiedPrefixSums = buildPrefixSums(prices);

    return Math.max(
        computeRangeSum(originalPrefixSums, 1, n),
        IntStream.rangeClosed(0, n - k)
            .mapToLong(
                beginIndex ->
                    computeRangeSum(originalPrefixSums, 1, beginIndex)
                        + computeRangeSum(originalPrefixSums, beginIndex + k + 1, n)
                        + computeRangeSum(
                            modifiedPrefixSums, beginIndex + k / 2 + 1, beginIndex + k))
            .max()
            .getAsLong());
  }

  long[] buildPrefixSums(int[] values) {
    long[] result = new long[values.length + 1];
    for (int i = 1; i < result.length; ++i) {
      result[i] = result[i - 1] + values[i - 1];
    }

    return result;
  }

  long computeRangeSum(long[] prefixSums, int beginLength, int endLength) {
    return prefixSums[endLength] - prefixSums[beginLength - 1];
  }
}