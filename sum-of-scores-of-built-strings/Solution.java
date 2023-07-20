// https://cp-algorithms.com/string/z-function.html

import java.util.Arrays;

class Solution {
  public long sumScores(String s) {
    int[] z = new int[s.length()];
    int l = 0;
    int r = 0;
    for (int i = 1; i < s.length(); ++i) {
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
