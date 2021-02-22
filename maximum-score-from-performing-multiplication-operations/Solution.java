import java.util.Arrays;

class Solution {
  public int maximumScore(int[] nums, int[] multipliers) {
    int[] scores = new int[1];
    for (int multiplier : multipliers) {
      int[] nextScores = new int[scores.length + 1];
      Arrays.fill(nextScores, Integer.MIN_VALUE);

      for (int leftLength = 0; leftLength < scores.length; ++leftLength) {
        int rightLength = scores.length - 1 - leftLength;

        nextScores[leftLength + 1] =
            Math.max(
                nextScores[leftLength + 1], scores[leftLength] + nums[leftLength] * multiplier);
        nextScores[leftLength] =
            Math.max(
                nextScores[leftLength],
                scores[leftLength] + nums[nums.length - 1 - rightLength] * multiplier);
      }

      scores = nextScores;
    }

    return Arrays.stream(scores).max().getAsInt();
  }
}
