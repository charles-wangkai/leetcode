class Solution {
  public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
    int n = energyDrinkA.length;

    long[][] dp = new long[n][2];
    dp[0][0] = energyDrinkA[0];
    dp[0][1] = energyDrinkB[0];
    for (int i = 1; i < dp.length; ++i) {
      dp[i][0] = Math.max(dp[i - 1][0], (i == 1) ? 0 : dp[i - 2][1]) + energyDrinkA[i];
      dp[i][1] = Math.max(dp[i - 1][1], (i == 1) ? 0 : dp[i - 2][0]) + energyDrinkB[i];
    }

    return Math.max(dp[n - 1][0], dp[n - 1][1]);
  }
}