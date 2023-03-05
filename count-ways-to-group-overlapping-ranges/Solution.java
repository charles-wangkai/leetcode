import java.util.Arrays;
import java.util.Comparator;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countWays(int[][] ranges) {
    Arrays.sort(ranges, Comparator.comparing(range -> range[0]));

    int result = 1;
    int maxEnd = -1;
    for (int[] range : ranges) {
      if (range[0] <= maxEnd) {
        maxEnd = Math.max(maxEnd, range[1]);
      } else {
        result = multiplyMod(result, 2);
        maxEnd = range[1];
      }
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
