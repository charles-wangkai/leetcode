import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int countPairs(List<List<Integer>> coordinates, int k) {
    Map<Point, Integer> pointToCount = new HashMap<>();
    for (List<Integer> coord : coordinates) {
      Point point = new Point(coord.get(0), coord.get(1));
      pointToCount.put(point, pointToCount.getOrDefault(point, 0) + 1);
    }

    long result = 0;
    for (List<Integer> coord : coordinates) {
      for (int i = 0; i <= k; ++i) {
        Point other = new Point(coord.get(0) ^ i, coord.get(1) ^ (k - i));

        result +=
            Math.max(
                0,
                pointToCount.getOrDefault(other, 0)
                    - (other.equals(new Point(coord.get(0), coord.get(1))) ? 1 : 0));
      }
    }

    return (int) (result / 2);
  }
}

record Point(int x, int y) {}
