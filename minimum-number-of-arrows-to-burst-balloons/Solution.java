import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, Comparator.comparing(p -> p[0]));

    int result = 1;
    int end = points[0][1];
    for (int i = 1; i < points.length; ++i) {
      if (points[i][0] > end) {
        ++result;

        end = points[i][1];
      } else {
        end = Math.min(end, points[i][1]);
      }
    }

    return result;
  }
}
