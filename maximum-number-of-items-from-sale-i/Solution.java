import java.util.Arrays;

class Solution {
  public int maximumSaleItems(int[][] items, int budget) {
    int[] freeCounts = new int[items.length];
    for (int i = 0; i < freeCounts.length; ++i) {
      for (int j = 0; j < items.length; ++j) {
        if (j != i && items[j][0] % items[i][0] == 0) {
          ++freeCounts[i];
        }
      }
    }

    int[] dp = new int[budget + 1];
    for (int i = 0; i < items.length; ++i) {
      int price = items[i][1];

      int[] nextDp = dp.clone();

      for (int j = 0; j + price <= budget; ++j) {
        nextDp[j + price] =
            Math.max(nextDp[j + price], Math.max(dp[j] + 1 + freeCounts[i], nextDp[j] + 1));
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}