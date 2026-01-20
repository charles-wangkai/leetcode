import java.util.Arrays;

class Solution {
  public int findMaxVal(int n, int[][] restrictions, int[] diff) {
    int[] maxLimits = new int[n];
    Arrays.fill(maxLimits, Integer.MAX_VALUE);
    for (int[] restriction : restrictions) {
      maxLimits[restriction[0]] = restriction[1];
    }

    for (int i = maxLimits.length - 2; i >= 0; --i) {
      maxLimits[i] =
          Math.min(
              maxLimits[i],
              (maxLimits[i + 1] == Integer.MAX_VALUE)
                  ? Integer.MAX_VALUE
                  : (maxLimits[i + 1] + diff[i]));
    }

    maxLimits[0] = 0;
    for (int i = 1; i < maxLimits.length; ++i) {
      maxLimits[i] =
          Math.min(
              maxLimits[i],
              (maxLimits[i - 1] == Integer.MAX_VALUE)
                  ? maxLimits[i - 1]
                  : (maxLimits[i - 1] + diff[i - 1]));
    }

    return Arrays.stream(maxLimits).max().getAsInt();
  }
}