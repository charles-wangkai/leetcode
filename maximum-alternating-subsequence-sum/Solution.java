class Solution {
  public long maxAlternatingSum(int[] nums) {
    long evenMaxSum = 0;
    long oddMaxSum = Long.MIN_VALUE;
    for (int num : nums) {
      long nextEvenMaxSum = evenMaxSum;
      long nextOddMaxSum = oddMaxSum;

      nextOddMaxSum = Math.max(nextOddMaxSum, evenMaxSum + num);

      if (oddMaxSum != Long.MIN_VALUE) {
        nextEvenMaxSum = Math.max(nextEvenMaxSum, oddMaxSum - num);
      }

      evenMaxSum = nextEvenMaxSum;
      oddMaxSum = nextOddMaxSum;
    }

    return Math.max(evenMaxSum, oddMaxSum);
  }
}
