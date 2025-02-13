import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public String minCostGoodCaption(String caption) {
    int[][][] dp = new int[caption.length()][26][4];
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp[i].length; ++j) {
        Arrays.fill(dp[i][j], Integer.MAX_VALUE);
      }
    }
    for (int l = 0; l < 26; ++l) {
      dp[dp.length - 1][l][1] = Math.abs(caption.charAt(caption.length() - 1) - 'a' - l);
    }
    for (int i = dp.length - 1; i >= 1; --i) {
      for (int l = 0; l < 26; ++l) {
        for (int count = 1; count <= 3; ++count) {
          if (dp[i][l][count] != Integer.MAX_VALUE) {
            for (int nextL = 0; nextL < 26; ++nextL) {
              if (nextL == l || count == 3) {
                int nextCount = (nextL == l) ? Math.min(3, count + 1) : 1;
                dp[i - 1][nextL][nextCount] =
                    Math.min(
                        dp[i - 1][nextL][nextCount],
                        dp[i][l][count] + Math.abs(caption.charAt(i - 1) - 'a' - nextL));
              }
            }
          }
        }
      }
    }

    int minCost = IntStream.range(0, 26).map(l -> dp[0][l][3]).min().getAsInt();
    if (minCost == Integer.MAX_VALUE) {
      return "";
    }

    StringBuilder result = new StringBuilder();
    int cost = 0;
    int prev = -1;
    int count = 3;
    for (int i = 0; i < dp.length; ++i) {
      for (int l = 0; ; ++l) {
        if (isPossible(dp, i, l, minCost, cost, prev, count)) {
          result.append((char) (l + 'a'));

          cost += Math.abs(caption.charAt(i) - 'a' - l);

          if (l == prev) {
            count = Math.min(3, count + 1);
          } else {
            count = 1;
          }

          prev = l;

          break;
        }
      }
    }

    return result.toString();
  }

  boolean isPossible(int[][][] dp, int index, int l, int minCost, int cost, int prev, int count) {
    if (l == prev) {
      return IntStream.rangeClosed(1, 3)
          .anyMatch(
              c ->
                  count + c >= 3
                      && dp[index][l][c] != Integer.MAX_VALUE
                      && cost + dp[index][l][c] == minCost);
    }

    return count == 3 && dp[index][l][3] != Integer.MAX_VALUE && cost + dp[index][l][3] == minCost;
  }
}