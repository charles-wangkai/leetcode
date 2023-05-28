class Solution {
  public int minExtraChar(String s, String[] dictionary) {
    int[] dp = new int[s.length() + 1];
    for (int i = 1; i < dp.length; ++i) {
      dp[i] = dp[i - 1] + 1;
      for (String word : dictionary) {
        if (s.substring(0, i).endsWith(word)) {
          dp[i] = Math.min(dp[i], dp[i - word.length()]);
        }
      }
    }

    return dp[dp.length - 1];
  }
}
