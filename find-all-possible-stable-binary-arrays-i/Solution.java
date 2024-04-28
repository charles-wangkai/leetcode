import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfStableArrays(int zero, int one, int limit) {
    int[][][] dp = new int[zero + one + 1][zero + 1][2];
    Arrays.fill(dp[0][0], 1);

    for (int length = 1; length < dp.length; ++length) {
      for (int c0 = 0; c0 <= Math.min(length, zero); ++c0) {
        int c1 = length - c0;
        if (c1 <= one) {
          for (int last = 1; last <= Math.min(limit, c0); ++last) {
            dp[length][c0][0] = addMod(dp[length][c0][0], dp[length - last][c0 - last][1]);
          }

          for (int last = 1; last <= Math.min(limit, length); ++last) {
            dp[length][c0][1] = addMod(dp[length][c0][1], dp[length - last][c0][0]);
          }
        }
      }
    }

    return addMod(dp[zero + one][zero][0], dp[zero + one][zero][1]);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}