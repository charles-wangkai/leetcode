class Solution {
  public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
    int common = Math.min(need1, need2);

    return (long) Math.min(cost1 + cost2, costBoth) * common
        + (long) Math.min(cost1, costBoth) * (need1 - common)
        + (long) Math.min(cost2, costBoth) * (need2 - common);
  }
}