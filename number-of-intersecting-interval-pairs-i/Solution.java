class Solution {
  public int countIntersectingIntervals(int[][] intervals) {
    int result = 0;
    for (int i = 0; i < intervals.length; ++i) {
      for (int j = i + 1; j < intervals.length; ++j) {
        if (isIntersect(intervals[i], intervals[j])) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isIntersect(int[] interval1, int[] interval2) {
    return !(interval1[1] < interval2[0] || interval2[1] < interval1[0]);
  }
}