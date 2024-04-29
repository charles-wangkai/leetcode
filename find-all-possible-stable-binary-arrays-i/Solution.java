import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfStableArrays(int zero, int one, int limit) {
    int[][][] dp = new int[zero + 1][one + 1][2];
    Arrays.fill(dp[0][0], 1);

    for (int length = 1; length <= zero + one; ++length) {
      for (int c0 = Math.max(0, length - one); c0 <= Math.min(length, zero); ++c0) {
        int c1 = length - c0;

        for (int last = 1; last <= Math.min(limit, c1); ++last) {
          dp[c0][c1][0] = addMod(dp[c0][c1][0], dp[c0][c1 - last][1]);
        }

        for (int last = 1; last <= Math.min(limit, c0); ++last) {
          dp[c0][c1][1] = addMod(dp[c0][c1][1], dp[c0 - last][c1][0]);
        }
      }
    }

    return addMod(dp[zero][one][0], dp[zero][one][1]);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}