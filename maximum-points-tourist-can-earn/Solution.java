import java.util.Arrays;

class Solution {
  public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
    int[] dp = new int[n];
    for (int d = 0; d < k; ++d) {
      int[] nextDp = new int[n];
      for (int i = 0; i < n; ++i) {
        nextDp[i] = Math.max(nextDp[i], dp[i] + stayScore[d][i]);
        for (int j = 0; j < n; ++j) {
          if (j != i) {
            nextDp[i] = Math.max(nextDp[i], dp[j] + travelScore[j][i]);
          }
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}