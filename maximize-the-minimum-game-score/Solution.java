import java.util.Arrays;

class Solution {
  public long maxScore(int[] points, int m) {
    long result = 0;
    long lower = 1;
    long upper = (long) Arrays.stream(points).max().getAsInt() * m;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(points, m, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] points, int m, long target) {
    long[] rests = Arrays.stream(points).mapToLong(point -> (target + point - 1) / point).toArray();

    int endIndex = rests.length - 1;
    while (rests[endIndex] == 0) {
      --endIndex;
    }

    for (int i = 0; i < endIndex; ++i) {
      if (rests[i] == 0) {
        if (m < 1) {
          return false;
        }
        --m;
      } else {
        if (m < 2 * rests[i] - 1) {
          return false;
        }
        m -= 2 * rests[i] - 1;

        if (i != rests.length - 1) {
          rests[i + 1] = Math.max(0, rests[i + 1] - (rests[i] - 1));
        }
      }
    }

    return rests[endIndex] == 0 || m >= 2 * rests[endIndex] - 1;
  }
}