import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparing(interval -> interval[1]));

    int nonOverlapCount = 0;
    int endTime = Integer.MIN_VALUE;
    for (int[] interval : intervals) {
      if (interval[0] >= endTime) {
        endTime = interval[1];
        ++nonOverlapCount;
      }
    }

    return intervals.length - nonOverlapCount;
  }
}
