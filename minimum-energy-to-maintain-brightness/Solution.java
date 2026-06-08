import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public long minEnergy(int n, int brightness, int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));

    int timeUnitNum = 0;
    int beginTime = intervals[0][0];
    int endTime = intervals[0][1];
    for (int i = 1; i < intervals.length; ++i) {
      if (intervals[i][0] <= endTime) {
        endTime = Math.max(endTime, intervals[i][1]);
      } else {
        timeUnitNum += endTime - beginTime + 1;

        beginTime = intervals[i][0];
        endTime = intervals[i][1];
      }
    }
    timeUnitNum += endTime - beginTime + 1;

    return Math.ceilDiv(brightness, 3L) * timeUnitNum;
  }
}