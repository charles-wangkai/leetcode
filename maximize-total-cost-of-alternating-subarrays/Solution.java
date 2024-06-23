class Solution {
  public long maximumTotalCost(int[] nums) {
    long maxPos = Long.MIN_VALUE;
    long maxNeg = 0;
    for (int num : nums) {
      long nextMaxPos = Math.max(maxPos, maxNeg) + num;
      if (maxNeg != Long.MIN_VALUE) {
        nextMaxPos = Math.max(nextMaxPos, maxNeg + num);
      }

      long nextMaxNeg = Long.MIN_VALUE;
      if (maxPos != Long.MIN_VALUE) {
        nextMaxNeg = Math.max(nextMaxNeg, maxPos - num);
      }

      maxPos = nextMaxPos;
      maxNeg = nextMaxNeg;
    }

    return Math.max(maxPos, maxNeg);
  }
}