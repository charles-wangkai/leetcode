// https://leetcode.com/problems/distribute-candies-among-children-iii/solutions/4268115/python-the-inclusion-exclusion-principal-o-1/

class Solution {
  public long distributeCandies(int n, int limit) {
    return computeWayNum(n)
        - 3 * computeWayNum(n - (limit + 1))
        + 3 * computeWayNum(n - 2 * (limit + 1))
        - computeWayNum(n - 3 * (limit + 1));
  }

  long computeWayNum(int candyNum) {
    return (candyNum < 0) ? 0 : ((candyNum + 2L) * (candyNum + 1) / 2);
  }
}
