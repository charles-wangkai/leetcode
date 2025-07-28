import java.util.stream.IntStream;

class Solution {
  public long numOfSubsequences(String s) {
    int[] lPrefixCounts = new int[s.length()];
    for (int i = 0; i < lPrefixCounts.length; ++i) {
      lPrefixCounts[i] = ((i == 0) ? 0 : lPrefixCounts[i - 1]) + ((s.charAt(i) == 'L') ? 1 : 0);
    }

    int[] tSuffixCounts = new int[s.length()];
    for (int i = tSuffixCounts.length - 1; i >= 0; --i) {
      tSuffixCounts[i] =
          ((i == tSuffixCounts.length - 1) ? 0 : tSuffixCounts[i + 1])
              + ((s.charAt(i) == 'T') ? 1 : 0);
    }

    return Math.max(
        IntStream.range(0, s.length() - 1)
                .mapToLong(i -> (long) lPrefixCounts[i] * tSuffixCounts[i + 1])
                .max()
                .orElse(0)
            + IntStream.range(0, s.length())
                .filter(i -> s.charAt(i) == 'C')
                .mapToLong(
                    i ->
                        ((i == 0) ? 0 : lPrefixCounts[i - 1])
                            * ((i == s.length() - 1) ? 0 : tSuffixCounts[i + 1]))
                .sum(),
        Math.max(
            IntStream.range(0, s.length())
                .filter(i -> s.charAt(i) == 'C')
                .mapToLong(
                    i ->
                        (((i == 0) ? 0 : lPrefixCounts[i - 1]) + 1L)
                            * ((i == s.length() - 1) ? 0 : tSuffixCounts[i + 1]))
                .sum(),
            IntStream.range(0, s.length())
                .filter(i -> s.charAt(i) == 'C')
                .mapToLong(
                    i ->
                        ((i == 0) ? 0 : lPrefixCounts[i - 1])
                            * (((i == s.length() - 1) ? 0 : tSuffixCounts[i + 1]) + 1L))
                .sum()));
  }
}