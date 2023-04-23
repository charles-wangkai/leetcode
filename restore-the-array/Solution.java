class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfArrays(String s, int k) {
    int kLength = String.valueOf(k).length();

    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      for (int j = 1; j <= Math.min(i, kLength); ++j) {
        String part = s.substring(i - j, i);
        if (part.charAt(0) != '0' && Long.parseLong(part) <= k) {
          dp[i] = addMod(dp[i], dp[i - j]);
        }
      }
    }

    return dp[dp.length - 1];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
