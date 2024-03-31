// https://www.geeksforgeeks.org/maximum-manhattan-distance-between-a-distinct-pair-from-n-coordinates/

import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int minimumDistance(int[][] points) {
    SortedMap<Integer, Integer> sumToCount = new TreeMap<>();
    SortedMap<Integer, Integer> diffToCount = new TreeMap<>();
    for (int[] point : points) {
      updateMap(sumToCount, point[0] + point[1], 1);
      updateMap(diffToCount, point[0] - point[1], 1);
    }

    int result = Integer.MAX_VALUE;
    for (int[] point : points) {
      updateMap(sumToCount, point[0] + point[1], -1);
      updateMap(diffToCount, point[0] - point[1], -1);

      result =
          Math.min(
              result,
              Math.max(
                  sumToCount.lastKey() - sumToCount.firstKey(),
                  diffToCount.lastKey() - diffToCount.firstKey()));

      updateMap(sumToCount, point[0] + point[1], 1);
      updateMap(diffToCount, point[0] - point[1], 1);
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}