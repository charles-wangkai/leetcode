import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumSaleItems(int[][] items, int budget) {
    int[] dp = new int[budget + 1];
    for (int i = 0; i < items.length; ++i) {
      int i_ = i;
      int freeCount =
          (int)
              IntStream.range(0, items.length)
                  .filter(j -> j != i_ && items[j][0] % items[i_][0] == 0)
                  .count();

      int price = items[i][1];

      int[] nextDp = dp.clone();

      for (int j = 0; j + price <= budget; ++j) {
        nextDp[j + price] =
            Math.max(nextDp[j + price], Math.max(dp[j] + 1 + freeCount, nextDp[j] + 1));
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}