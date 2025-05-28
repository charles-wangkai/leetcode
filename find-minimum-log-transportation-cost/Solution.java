class Solution {
  public long minCuttingCost(int n, int m, int k) {
    return computeCost(k, n) + computeCost(k, m);
  }

  long computeCost(int k, int length) {
    int length1 = Math.min(k, length);
    int length2 = length - length1;

    return (long) length1 * length2;
  }
}