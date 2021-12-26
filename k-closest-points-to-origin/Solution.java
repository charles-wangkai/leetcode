import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[][] kClosest(int[][] points, int k) {
    return Arrays.stream(points)
        .sorted(Comparator.comparing(this::computeDistanceSquare))
        .limit(k)
        .toArray(int[][]::new);
  }

  int computeDistanceSquare(int[] point) {
    return point[0] * point[0] + point[1] * point[1];
  }
}
