import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfStableArrays(int zero, int one, int limit) {
    int[][][] dp = new int[zero + 1][one + 1][2];
    int[][][] upPrefixSums = new int[zero + 1][one + 1][2];
    int[][][] leftPrefixSums = new int[zero + 1][one + 1][2];

    for (int length = 0; length <= zero + one; ++length) {
      for (int c0 = Math.max(0, length - one); c0 <= Math.min(length, zero); ++c0) {
        int c1 = length - c0;

        if (length == 0) {
          Arrays.fill(dp[c0][c1], 1);
        } else {
          dp[c0][c1][0] =
              addMod(
                  getValue(leftPrefixSums, c0, c1 - 1, 1),
                  -getValue(leftPrefixSums, c0, c1 - Math.min(limit, c1) - 1, 1));
          dp[c0][c1][1] =
              addMod(
                  getValue(upPrefixSums, c0 - 1, c1, 0),
                  -getValue(upPrefixSums, c0 - Math.min(limit, c0) - 1, c1, 0));
        }

        for (int d = 0; d <= 1; ++d) {
          upPrefixSums[c0][c1][d] =
              addMod((c0 == 0) ? 0 : upPrefixSums[c0 - 1][c1][d], dp[c0][c1][d]);
          leftPrefixSums[c0][c1][d] =
              addMod((c1 == 0) ? 0 : leftPrefixSums[c0][c1 - 1][d], dp[c0][c1][d]);
        }
      }
    }

    return addMod(dp[zero][one][0], dp[zero][one][1]);
  }

  int getValue(int[][][] a, int c0, int c1, int d) {
    return (c0 >= 0 && c1 >= 0) ? a[c0][c1][d] : 0;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}