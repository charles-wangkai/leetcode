import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int longestSubsequence(String s, int k) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (char c : s.toCharArray()) {
      for (int i = dp.length - 1; i >= 1; --i) {
        if (dp[i - 1] != Integer.MAX_VALUE && dp[i - 1] * 2 + (c - '0') <= k) {
          dp[i] = Math.min(dp[i], dp[i - 1] * 2 + (c - '0'));
        }
      }
    }

    return IntStream.range(0, dp.length).filter(i -> dp[i] != Integer.MAX_VALUE).max().getAsInt();
  }
}