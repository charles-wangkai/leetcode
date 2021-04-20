class Solution {
  static final int MODULUS = 1_000_000_007;

  public int waysToDistribute(int n, int k) {
    int[][] wayNums = new int[n + 1][k + 1];
    wayNums[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= k; ++j) {
        wayNums[i][j] =
            addMod(wayNums[i][j], addMod(multiplyMod(wayNums[i - 1][j], j), wayNums[i - 1][j - 1]));
      }
    }

    return wayNums[n][k];
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
