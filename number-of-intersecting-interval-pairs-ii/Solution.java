import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public long countIntersectingIntervals(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));

    return intervals.length * (intervals.length - 1L) / 2
        - Arrays.stream(intervals)
            .mapToInt(interval -> computeNonIntersectNum(intervals, interval[1] + 1))
            .asLongStream()
            .sum();
  }

  int computeNonIntersectNum(int[][] intervals, int minStart) {
    int index = intervals.length;
    int lower = 0;
    int upper = intervals.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (intervals[middle][0] >= minStart) {
        index = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return intervals.length - index;
  }
}