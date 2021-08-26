import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimizeTheDifference(int[][] mat, int target) {
    int minSum = Arrays.stream(mat).mapToInt(line -> Arrays.stream(line).min().getAsInt()).sum();
    if (minSum >= target) {
      return minSum - target;
    }

    boolean[] sums = new boolean[target * 2 + 1];
    sums[0] = true;

    for (int[] line : mat) {
      boolean[] nextSums = new boolean[sums.length];
      for (int i = 0; i < sums.length; ++i) {
        if (sums[i]) {
          for (int element : line) {
            int nextSum = i + element;
            if (nextSum < nextSums.length) {
              nextSums[nextSum] = true;
            }
          }
        }
      }

      sums = nextSums;
    }

    boolean[] sums_ = sums;
    return IntStream.range(0, sums.length)
        .filter(i -> sums_[i])
        .map(i -> Math.abs(i - target))
        .min()
        .getAsInt();
  }
}
