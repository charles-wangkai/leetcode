// https://cp-algorithms.com/string/z-function.html

import java.util.stream.IntStream;

class Solution {
  static final char SEPARATOR = '#';

  public int minStartingIndex(String s, String pattern) {
    int[] prefixLengths = buildPrefixLengths(s, pattern);
    int[] suffixLengths = buildSuffixLengths(s, pattern);

    return IntStream.rangeClosed(0, s.length() - pattern.length())
        .filter(
            i -> prefixLengths[i] + suffixLengths[i + pattern.length() - 1] >= pattern.length() - 1)
        .findFirst()
        .orElse(-1);
  }

  int[] buildPrefixLengths(String s, String pattern) {
    int[] z = zFunction(pattern + SEPARATOR + s);

    return IntStream.range(pattern.length() + 1, z.length).map(i -> z[i]).toArray();
  }

  int[] buildSuffixLengths(String s, String pattern) {
    int[] z = zFunction(reverse(pattern) + SEPARATOR + reverse(s));

    return IntStream.range(0, s.length()).map(i -> z[z.length - 1 - i]).toArray();
  }

  String reverse(String x) {
    return new StringBuilder(x).reverse().toString();
  }

  int[] zFunction(String s) {
    int n = s.length();
    int[] z = new int[n];
    int l = 0;
    int r = 0;
    for (int i = 1; i < n; ++i) {
      if (i < r) {
        z[i] = Math.min(r - i, z[i - l]);
      }
      while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
        ++z[i];
      }
      if (i + z[i] > r) {
        l = i;
        r = i + z[i];
      }
    }

    return z;
  }
}
