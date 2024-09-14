class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfWays(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int coin : new int[] {1, 2, 6}) {
      for (int i = coin; i < dp.length; ++i) {
        dp[i] = addMod(dp[i], dp[i - coin]);
      }
    }

    return addMod(dp[n], addMod((n >= 4) ? dp[n - 4] : 0, (n >= 8) ? dp[n - 8] : 0));
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}