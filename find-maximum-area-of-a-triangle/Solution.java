import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long maxArea(int[][] coords) {
    SortedMap<Integer, Integer> yToMinX = new TreeMap<>();
    SortedMap<Integer, Integer> yToMaxX = new TreeMap<>();
    for (int[] coord : coords) {
      yToMinX.put(coord[1], Math.min(coord[0], yToMinX.getOrDefault(coord[1], Integer.MAX_VALUE)));
      yToMaxX.put(coord[1], Math.max(coord[0], yToMaxX.getOrDefault(coord[1], Integer.MIN_VALUE)));
    }

    SortedMap<Integer, Integer> xToMinY = new TreeMap<>();
    SortedMap<Integer, Integer> xToMaxY = new TreeMap<>();
    for (int[] coord : coords) {
      xToMinY.put(coord[0], Math.min(coord[1], xToMinY.getOrDefault(coord[0], Integer.MAX_VALUE)));
      xToMaxY.put(coord[0], Math.max(coord[1], xToMaxY.getOrDefault(coord[0], Integer.MIN_VALUE)));
    }

    long result = -1;
    for (int y : yToMinX.keySet()) {
      if (!yToMinX.get(y).equals(yToMaxX.get(y))) {
        if (yToMinX.lastKey() != y) {
          result =
              Math.max(result, (long) (yToMaxX.get(y) - yToMinX.get(y)) * (yToMinX.lastKey() - y));
        }
        if (yToMinX.firstKey() != y) {
          result =
              Math.max(result, (long) (yToMaxX.get(y) - yToMinX.get(y)) * (y - yToMinX.firstKey()));
        }
      }
    }
    for (int x : xToMinY.keySet()) {
      if (!xToMinY.get(x).equals(xToMaxY.get(x))) {
        if (xToMinY.lastKey() != x) {
          result =
              Math.max(result, (long) (xToMaxY.get(x) - xToMinY.get(x)) * (xToMinY.lastKey() - x));
        }
        if (xToMinY.firstKey() != x) {
          result =
              Math.max(result, (long) (xToMaxY.get(x) - xToMinY.get(x)) * (x - xToMinY.firstKey()));
        }
      }
    }

    return result;
  }
}