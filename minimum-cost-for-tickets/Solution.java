class Solution {
  public int mincostTickets(int[] days, int[] costs) {
    int[] dp = new int[days.length + 1];

    for (int i = 1; i < dp.length; ++i) {
      dp[i] =
          Math.min(
              Math.min(
                  dp[findLastLength(days, i, 1)] + costs[0],
                  dp[findLastLength(days, i, 7)] + costs[1]),
              dp[findLastLength(days, i, 30)] + costs[2]);
    }

    return dp[dp.length - 1];
  }

  int findLastLength(int[] days, int length, int pass) {
    int result = length - 1;
    while (result != 0 && days[length - 1] - days[result - 1] + 1 <= pass) {
      --result;
    }

    return result;
  }
}
