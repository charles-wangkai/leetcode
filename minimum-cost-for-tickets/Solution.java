class Solution {
  public int mincostTickets(int[] days, int[] costs) {
    int[] minCosts = new int[days.length + 1];

    for (int i = 1; i < minCosts.length; ++i) {
      minCosts[i] =
          Math.min(
              Math.min(
                  minCosts[findLastLength(days, i, 1)] + costs[0],
                  minCosts[findLastLength(days, i, 7)] + costs[1]),
              minCosts[findLastLength(days, i, 30)] + costs[2]);
    }

    return minCosts[days.length];
  }

  int findLastLength(int[] days, int length, int pass) {
    int lastLength = length - 1;
    while (lastLength != 0 && days[length - 1] - days[lastLength - 1] + 1 <= pass) {
      --lastLength;
    }

    return lastLength;
  }
}
