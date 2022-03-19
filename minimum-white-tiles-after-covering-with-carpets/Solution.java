import java.util.Arrays;

class Solution {
  public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
    int[][] dp = new int[floor.length() + 1][numCarpets + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int i = 0; i < floor.length(); ++i) {
      for (int j = 0; j <= numCarpets; ++j) {
        if (dp[i][j] != Integer.MAX_VALUE) {
          dp[i + 1][j] = dp[i][j] + (floor.charAt(i) - '0');
        }
        if (j != 0) {
          dp[i + 1][j] = Math.min(dp[i + 1][j], dp[Math.max(0, i + 1 - carpetLen)][j - 1]);
        }
      }
    }

    return Arrays.stream(dp[floor.length()]).min().getAsInt();
  }
}