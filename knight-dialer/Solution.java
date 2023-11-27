import java.util.Arrays;

class Solution {
  static final int MODULUS = 1000000007;

  public int knightDialer(int n) {
    int[] dp = new int[10];
    Arrays.fill(dp, 1);

    for (int i = 0; i < n - 1; i++) {
      int[] nextDp = new int[10];
      nextDp[0] = addMod(dp[4], dp[6]);
      nextDp[1] = addMod(dp[6], dp[8]);
      nextDp[2] = addMod(dp[7], dp[9]);
      nextDp[3] = addMod(dp[4], dp[8]);
      nextDp[4] = addMod(addMod(dp[0], dp[3]), dp[9]);
      nextDp[5] = 0;
      nextDp[6] = addMod(addMod(dp[0], dp[1]), dp[7]);
      nextDp[7] = addMod(dp[2], dp[6]);
      nextDp[8] = addMod(dp[1], dp[3]);
      nextDp[9] = addMod(dp[2], dp[4]);

      dp = nextDp;
    }

    return Arrays.stream(dp).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
