class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfWays(int n, int x, int y) {
    int[] dp = new int[x + 1];
    dp[1] = x;
    for (int i = 0; i < n - 1; ++i) {
      for (int j = dp.length - 1; j >= 1; --j) {
        dp[j] = addMod(multiplyMod(dp[j], j), multiplyMod(dp[j - 1], x - j + 1));
      }
    }

    int result = 0;
    int yPower = 1;
    for (int i = 0; i < dp.length; ++i) {
      result = addMod(result, multiplyMod(dp[i], yPower));
      yPower = multiplyMod(yPower, y);
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}