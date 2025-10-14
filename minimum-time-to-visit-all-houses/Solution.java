import java.util.stream.IntStream;

class Solution {
  public long minTotalTime(int[] forward, int[] backward, int[] queries) {
    long[] forwardPrefixSums = new long[forward.length];
    for (int i = 0; i < forwardPrefixSums.length; ++i) {
      forwardPrefixSums[i] = ((i == 0) ? 0 : forwardPrefixSums[i - 1]) + forward[i];
    }

    long[] backwardSuffixSums = new long[backward.length];
    for (int i = backwardSuffixSums.length - 1; i >= 0; --i) {
      backwardSuffixSums[i] =
          ((i == backwardSuffixSums.length - 1) ? 0 : backwardSuffixSums[i + 1]) + backward[i];
    }

    return IntStream.range(0, queries.length)
        .mapToLong(
            i ->
                computeTime(
                    forwardPrefixSums,
                    backwardSuffixSums,
                    ((i == 0) ? 0 : queries[i - 1]),
                    queries[i]))
        .sum();
  }

  long computeTime(long[] forwardPrefixSums, long[] backwardSuffixSums, int from, int to) {
    return Math.min(
        computeForwardTime(forwardPrefixSums, from, to),
        computeBackwardTime(backwardSuffixSums, from, to));
  }

  long computeForwardTime(long[] forwardPrefixSums, int from, int to) {
    if (from < to) {
      return forwardPrefixSums[to - 1] - ((from == 0) ? 0 : forwardPrefixSums[from - 1]);
    }

    return forwardPrefixSums[forwardPrefixSums.length - 1]
        - (forwardPrefixSums[from - 1] - ((to == 0) ? 0 : forwardPrefixSums[to - 1]));
  }

  long computeBackwardTime(long[] backwardSuffixSums, int from, int to) {
    if (from > to) {
      return backwardSuffixSums[to + 1]
          - ((from == backwardSuffixSums.length - 1) ? 0 : backwardSuffixSums[from + 1]);
    }

    return backwardSuffixSums[0]
        - (backwardSuffixSums[from + 1]
            - ((to == backwardSuffixSums.length - 1) ? 0 : backwardSuffixSums[to + 1]));
  }
}