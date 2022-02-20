import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int removeCoveredIntervals(int[][] intervals) {
    Arrays.sort(
        intervals,
        Comparator.comparing((int[] i) -> i[0])
            .thenComparing(Comparator.comparing((int[] i) -> i[1]).reversed()));

    int result = 0;
    int right = -1;
    for (int[] interval : intervals) {
      if (interval[1] > right) {
        right = interval[1];
        ++result;
      }
    }

    return result;
  }
}
