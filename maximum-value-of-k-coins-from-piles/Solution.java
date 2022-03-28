import java.util.Arrays;
import java.util.List;

class Solution {
  public int maxValueOfCoins(List<List<Integer>> piles, int k) {
    int[] maxSums = new int[k + 1];
    Arrays.fill(maxSums, -1);
    maxSums[0] = 0;

    for (List<Integer> pile : piles) {
      int[] nextMaxSums = maxSums.clone();

      int addition = 0;
      for (int i = 0; i < pile.size(); ++i) {
        addition += pile.get(i);
        for (int j = i + 1; j < nextMaxSums.length; ++j) {
          if (maxSums[j - i - 1] != -1) {
            nextMaxSums[j] = Math.max(nextMaxSums[j], maxSums[j - i - 1] + addition);
          }
        }
      }

      maxSums = nextMaxSums;
    }

    return maxSums[k];
  }
}