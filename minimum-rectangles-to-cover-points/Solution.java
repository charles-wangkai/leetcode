import java.util.Arrays;

class Solution {
  public int minRectanglesToCoverPoints(int[][] points, int w) {
    int[] xs = Arrays.stream(points).mapToInt(point -> point[0]).sorted().toArray();

    int result = 0;
    int limit = -1;
    for (int x : xs) {
      if (x > limit) {
        ++result;
        limit = x + w;
      }
    }

    return result;
  }
}