class Solution {
  public int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      if (s.charAt(i - 1) != '0') {
        dp[i] += dp[i - 1];
      }

      if (i != 1) {
        int twoDigit = Integer.parseInt(s.substring(i - 2, i));
        if (twoDigit >= 10 && twoDigit <= 26) {
          dp[i] += dp[i - 2];
        }
      }
    }

    return dp[dp.length - 1];
  }
}
