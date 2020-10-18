class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfSets(int n, int k) {
    int[][][] wayNums = new int[n + 1][k + 1][3];
    wayNums[0][0][0] = 1;

    for (int i = 1; i <= n; ++i) {
      for (int j = 0; j <= k; ++j) {
        wayNums[i][j][0] =
            (j == 0) ? wayNums[i - 1][j][0] : addMod(wayNums[i - 1][j][0], wayNums[i][j - 1][2]);
        wayNums[i][j][1] = wayNums[i][j][0];
        wayNums[i][j][2] = addMod(wayNums[i - 1][j][1], wayNums[i - 1][j][2]);
      }
    }

    return wayNums[n][k][0];
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
