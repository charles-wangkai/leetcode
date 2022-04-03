// https://cp-algorithms.com/string/z-function.html

import java.util.Arrays;

class Solution {
  public long sumScores(String s) {
    int[] z = new int[s.length()];
    for (int i = 1, l = 0, r = 0; i < s.length(); ++i) {
      if (i <= r) {
        z[i] = Math.min(r - i + 1, z[i - l]);
      }
      while (i + z[i] < s.length() && s.charAt(z[i]) == s.charAt(i + z[i])) {
        ++z[i];
      }
      if (i + z[i] - 1 > r) {
        l = i;
        r = i + z[i] - 1;
      }
    }

    return Arrays.stream(z).asLongStream().sum() + s.length();
  }
}