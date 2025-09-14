class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countStableSubsequences(int[] nums) {
    int[][] dp = new int[3][3];
    dp[2][2] = 1;

    for (int num : nums) {
      int current = num % 2;

      int[][] nextDp = new int[3][];
      for (int i = 0; i < nextDp.length; ++i) {
        nextDp[i] = dp[i].clone();
      }
      for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
          if (i != current || j != current) {
            nextDp[j][current] = addMod(nextDp[j][current], dp[i][j]);
          }
        }
      }

      dp = nextDp;
    }

    int result = 0;
    for (int i = 0; i <= 2; ++i) {
      for (int j = 0; j <= 1; ++j) {
        result = addMod(result, dp[i][j]);
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}