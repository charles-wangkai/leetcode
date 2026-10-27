// https://leetcode.com/problems/minimum-cost-to-split-into-ones/solutions/7618346/take-one-by-one-with-proof-recursive-on-bckny/

class Solution {
  public long minCost(int n) {
    return n * (n - 1L) / 2;
  }
}