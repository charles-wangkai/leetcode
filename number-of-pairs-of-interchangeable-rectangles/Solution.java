import java.util.HashMap;
import java.util.Map;

class Solution {
  public long interchangeableRectangles(int[][] rectangles) {
    Map<String, Integer> ratioToCount = new HashMap<>();
    for (int[] rectangle : rectangles) {
      int g = gcd(rectangle[0], rectangle[1]);
      String ratio = String.format("%d/%d", rectangle[0] / g, rectangle[1] / g);
      ratioToCount.put(ratio, ratioToCount.getOrDefault(ratio, 0) + 1);
    }

    return ratioToCount.values().stream().mapToLong(count -> count * (count - 1L) / 2).sum();
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
