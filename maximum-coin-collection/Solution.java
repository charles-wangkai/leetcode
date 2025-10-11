import java.util.Arrays;

class Solution {
  public long maxCoins(int[] lane1, int[] lane2) {
    long[][] dp = new long[2][3];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Long.MIN_VALUE);
    }

    long result = Long.MIN_VALUE;
    for (int i = 0; i < lane1.length; ++i) {
      long[][] nextDp = new long[2][3];
      for (int j = 0; j < dp.length; ++j) {
        Arrays.fill(nextDp[j], Long.MIN_VALUE);
      }
      nextDp[0][0] = lane1[i];
      nextDp[1][1] = lane2[i];

      for (int l = 0; l < dp.length; ++l) {
        for (int switchNum = 0; switchNum < dp[l].length; ++switchNum) {
          if (dp[l][switchNum] != Long.MIN_VALUE) {
            nextDp[l][switchNum] =
                Math.max(nextDp[l][switchNum], dp[l][switchNum] + ((l == 0) ? lane1 : lane2)[i]);

            if (switchNum != 2) {
              nextDp[1 - l][switchNum + 1] =
                  Math.max(
                      nextDp[1 - l][switchNum + 1],
                      dp[l][switchNum] + ((l == 0) ? lane2 : lane1)[i]);
            }
          }
        }
      }

      for (int l = 0; l < dp.length; ++l) {
        for (int switchNum = 0; switchNum < dp[l].length; ++switchNum) {
          result = Math.max(result, nextDp[l][switchNum]);
        }
      }

      dp = nextDp;
    }

    return result;
  }
}