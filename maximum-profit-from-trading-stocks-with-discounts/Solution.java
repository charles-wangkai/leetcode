// https://leetcode.com/problems/maximum-profit-from-trading-stocks-with-discounts/solutions/6778488/detailed-editorial-tree-dp-dfs-c-python-java/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int[] h : hierarchy) {
      childLists[h[0] - 1].add(h[1] - 1);
    }

    int[][][] dp = new int[n][2][budget + 1];
    search(dp, present, future, budget, childLists, 0);

    return Arrays.stream(dp[0][0]).max().getAsInt();
  }

  void search(
      int[][][] dp, int[] present, int[] future, int budget, List<Integer>[] childLists, int node) {
    for (int child : childLists[node]) {
      search(dp, present, future, budget, childLists, child);
    }

    for (int parentBought = 0; parentBought < 2; ++parentBought) {
      int[] notBuyDp = new int[budget + 1];
      for (int child : childLists[node]) {
        int[] nextNotBuyDp = new int[notBuyDp.length];
        for (int b1 = 0; b1 <= budget; ++b1) {
          for (int b2 = 0; b1 + b2 <= budget; ++b2) {
            nextNotBuyDp[b1 + b2] =
                Math.max(nextNotBuyDp[b1 + b2], notBuyDp[b1] + dp[child][0][b2]);
          }
        }

        notBuyDp = nextNotBuyDp;
      }

      for (int b = 0; b <= budget; ++b) {
        dp[node][parentBought][b] = Math.max(dp[node][parentBought][b], notBuyDp[b]);
      }

      int price = (parentBought == 0) ? present[node] : (present[node] / 2);
      if (price <= budget) {
        int[] buyDp = new int[budget + 1];
        for (int child : childLists[node]) {
          int[] nextBuyDp = new int[buyDp.length];
          for (int b1 = 0; b1 <= budget; ++b1) {
            for (int b2 = 0; b1 + b2 <= budget; ++b2) {
              nextBuyDp[b1 + b2] = Math.max(nextBuyDp[b1 + b2], buyDp[b1] + dp[child][1][b2]);
            }
          }

          buyDp = nextBuyDp;
        }

        for (int b = price; b <= budget; ++b) {
          dp[node][parentBought][b] =
              Math.max(dp[node][parentBought][b], buyDp[b - price] + (future[node] - price));
        }
      }
    }
  }
}
