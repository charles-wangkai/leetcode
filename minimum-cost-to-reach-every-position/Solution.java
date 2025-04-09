class Solution {
  public int[] minCosts(int[] cost) {
    int[] result = new int[cost.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = Math.min((i == 0) ? Integer.MAX_VALUE : result[i - 1], cost[i]);
    }

    return result;
  }
}