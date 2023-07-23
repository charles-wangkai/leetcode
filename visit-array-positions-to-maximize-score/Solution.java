class Solution {
  public long maxScore(int[] nums, int x) {
    long evenMaxScore = Long.MIN_VALUE;
    long oddMaxScore = Long.MIN_VALUE;
    for (int num : nums) {
      if (num % 2 == 0) {
        if (evenMaxScore != Long.MIN_VALUE) {
          evenMaxScore += num;
        }
        if (oddMaxScore != Long.MIN_VALUE) {
          evenMaxScore = Math.max(evenMaxScore, oddMaxScore + num - x);
        }

        if (evenMaxScore == Long.MIN_VALUE) {
          evenMaxScore = num;
        }
      } else {
        if (oddMaxScore != Long.MIN_VALUE) {
          oddMaxScore += num;
        }
        if (evenMaxScore != Long.MIN_VALUE) {
          oddMaxScore = Math.max(oddMaxScore, evenMaxScore + num - x);
        }

        if (oddMaxScore == Long.MIN_VALUE) {
          oddMaxScore = num;
        }
      }
    }

    return Math.max(evenMaxScore, oddMaxScore);
  }
}
