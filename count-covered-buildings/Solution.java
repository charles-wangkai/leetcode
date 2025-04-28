import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countCoveredBuildings(int n, int[][] buildings) {
    Map<Integer, Integer> xToMinY = new HashMap<>();
    Map<Integer, Integer> xToMaxY = new HashMap<>();
    Map<Integer, Integer> yToMinX = new HashMap<>();
    Map<Integer, Integer> yToMaxX = new HashMap<>();
    for (int[] building : buildings) {
      xToMinY.put(
          building[0], Math.min(xToMinY.getOrDefault(building[0], Integer.MAX_VALUE), building[1]));
      xToMaxY.put(
          building[0], Math.max(xToMaxY.getOrDefault(building[0], Integer.MIN_VALUE), building[1]));
      yToMinX.put(
          building[1], Math.min(yToMinX.getOrDefault(building[1], Integer.MAX_VALUE), building[0]));
      yToMaxX.put(
          building[1], Math.max(yToMaxX.getOrDefault(building[1], Integer.MIN_VALUE), building[0]));
    }

    return (int)
        Arrays.stream(buildings)
            .filter(
                building ->
                    building[0] != yToMinX.get(building[1])
                        && building[0] != yToMaxX.get(building[1])
                        && building[1] != xToMinY.get(building[0])
                        && building[1] != xToMaxY.get(building[0]))
            .count();
  }
}