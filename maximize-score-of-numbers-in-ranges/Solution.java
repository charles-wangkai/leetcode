import java.util.Arrays;

class Solution {
  public int maxPossibleScore(int[] start, int d) {
    Arrays.sort(start);

    int result = -1;
    int lower = 0;
    int upper = start[start.length - 1] + d - start[0];
    while (lower <= upper) {
      int middle = lower + (upper - lower) / 2;
      if (check(start, d, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] start, int d, int minDiff) {
    long minTarget = start[0] + minDiff;
    for (int i = 1; i < start.length; ++i) {
      if (start[i] + d < minTarget) {
        return false;
      }

      minTarget = Math.max(minTarget, start[i]) + minDiff;
    }

    return true;
  }
}