import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int longestSubsequence(String s, int k) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (char c : s.toCharArray()) {
      int[] nextDp = dp.clone();
      for (int i = nextDp.length - 1; i >= 1; --i) {
        if (dp[i - 1] != Integer.MAX_VALUE && dp[i - 1] * 2 + (c - '0') <= k) {
          nextDp[i] = Math.min(nextDp[i], dp[i - 1] * 2 + (c - '0'));
        }
      }

      dp = nextDp;
    }

    int[] dp_ = dp;
    return IntStream.range(0, dp.length).filter(i -> dp_[i] != Integer.MAX_VALUE).max().getAsInt();
  }
}