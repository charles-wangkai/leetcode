class Solution {
  public int minCostClimbingStairs(int[] cost) {
    int prev = 0;
    int curr = 0;
    for (int i = 2; i <= cost.length; ++i) {
      int next = Math.min(prev + cost[i - 2], curr + cost[i - 1]);

      prev = curr;
      curr = next;
    }

    return curr;
  }
}
