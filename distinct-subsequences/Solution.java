class Solution {
  public int numDistinct(String s, String t) {
    int[] dp = new int[t.length() + 1];
    dp[0] = 1;

    for (char c : s.toCharArray()) {
      for (int i = t.length(); i >= 1; --i) {
        if (c == t.charAt(i - 1)) {
          dp[i] += dp[i - 1];
        }
      }
    }

    return dp[t.length()];
  }
}
