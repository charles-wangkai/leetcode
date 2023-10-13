class Solution {
  public int minCostClimbingStairs(int[] cost) {
    int prev = 0;
    int curr = 0;
    for (int i = 0; i < cost.length; ++i) {
      int next = Math.min(prev, curr) + cost[i];

      prev = curr;
      curr = next;
    }

    return Math.min(prev, curr);
  }
}
