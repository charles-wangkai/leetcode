// https://leetcode.com/problems/minimum-operations-to-make-character-frequencies-equal/solutions/6147746/those-who-know/

import java.util.stream.IntStream;

class Solution {
  public int makeStringGood(String s) {
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    return IntStream.rangeClosed(0, s.length())
        .map(
            m -> {
              int dp1 = 0;
              int dp2 = 0;
              int free = 0;
              for (int i = 0; i < counts.length; ++i) {
                int nextDp1;
                int nextDp2;
                int nextFree;
                if (counts[i] >= m) {
                  nextDp1 = Math.min(dp1, dp2) + (counts[i] - m);
                  nextDp2 = Integer.MAX_VALUE;
                  nextFree = counts[i] - m;
                } else {
                  nextDp1 = Math.min(dp1, dp2) + counts[i];
                  nextDp2 =
                      Math.min(
                          dp1 + Math.max(0, m - counts[i] - free),
                          (dp2 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (dp2 + (m - counts[i])));
                  nextFree = counts[i];
                }

                dp1 = nextDp1;
                dp2 = nextDp2;
                free = nextFree;
              }

              return Math.min(dp1, dp2);
            })
        .min()
        .getAsInt();
  }
}