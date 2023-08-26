import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int findLongestChain(int[][] pairs) {
    Arrays.sort(pairs, Comparator.comparing(pair -> pair[0]));

    int[] dp = new int[pairs.length];
    Arrays.fill(dp, 1);
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (pairs[j][1] < pairs[i][0]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}
